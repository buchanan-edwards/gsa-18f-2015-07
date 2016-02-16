package com.semanticbits.label.service

import org.codehaus.groovy.grails.commons.GrailsApplication

import groovy.json.JsonSlurper

/**
 * Searches drug label database using openFDA REST API
 * @author gopal
 *
 */
class SearchService {

            private static final String UNDERSCORE_CHAR = '_'

    private static final String SPACE_CHAR = ' '

    private static final int OPENFDA_MAXSKIP = 5000

    GrailsApplication grailsApplication
    OpenFDAService openFDAService
    
    /**
     * Search for a term, return the result from the specified page
     * @param term search term(s)
     * @param page result page number to return, page index start from 0 
     * @return Search results in a Map<String, Object> containing the following data
     * {
     *  totalCount: 10 
     *  totalPages: 1
     *  currentPage: 0
     *  currentCount: 10
     *  labels:[
     *  {id:'f229e866-5775-4e42-a316-8480dd92fec6',
     *   title:'TITANIUM DIOXIDE, OCTINOXATE, ZINC OXIDE',
     *   description:'a description'
     *  }
     *  ...
     *  ]
     * }
     * @throws LabelServiceException
     */
    Map<String, Object> search(String term='', int page = 0) throws LabelServiceException {
        log.debug("Searching for term ${term}, page=${page}")
        int skip = page * grailsApplication.config.itemsPerPage 
        if (skip >= OPENFDA_MAXSKIP) {
            throw new LabelServiceException('Pagination beyod 5000 records is not supported by openAPI')
        }
        String response = openFDAService.invoke(['search' : sanitizeSearchTerm(term), 
                                                  'limit' :  grailsApplication.config.itemsPerPage,
                                                  'skip' : skip])
        log.debug("Retrieved response ${response} from openFDA")
        generateResponse(response)
    }
    
    /**
     * 
     * @param labelId id of the label to be retrieved
     * @return A map containing the all the label details returned by the openFDA API
     *         The map will have extract same structure represented by the labels JSON except the following
     *          - Any attribute value with a prefix same as the attribute name, will sanitized by removing the prefix
     *            (e.g DESCRIPTION, WARNING etc)
     *          - A title attribute will be added to the root of the map. The title attribute value will be set to one
     *          of openfda.generic_name, openfda.brand_name or id it that order of preference
     * @throws LabelServiceException when there are errors fetching the label details
     */
    Map<String, Object> getLabelDetails(String labelId) throws LabelServiceException {
        if (!labelId) {
            throw new LabelServiceException('A valid label id must be provided')
        }        
        Map jsonResponse = new JsonSlurper().parseText(openFDAService.invoke([search : "id:${labelId}"]))
        Map result = sanitizeLabelAttributeValues(jsonResponse.results[0])
        result.title =  getLabelTitle(jsonResponse.results[0])
        result   
    }

    /**
     * Sanitize the input search term per the openFDA API rules
     * @param term search term
     * @return sanitized term
     */
    private String sanitizeSearchTerm(String term) {
        String cleanTerm = term ?: ''
        // Strip off first #
        if (term.startsWith('#')) {
            cleanTerm = term[1.. -1]
        }
        
        // Replace all spaces with +, https://open.fda.gov/api/reference/#spaces
        cleanTerm.replaceAll(SPACE_CHAR, '+')
    }

    /**
     * Generate the response JSON string to be returned
     * @param response response returned by the openFDA API
     * @return JSON map in the format
     * [ {id:'93321bf0-d58d-4ac1-bfa7-cb033ca9df85', name:'label', description: 'A description of the label' },
     *  {id:'93321bf0-d58d-4ac1-bfa7-1234434', name:'label 2', description: 'A description of the label 2' }
     * ] 
     */
    private generateResponse(String response) {
        try {
            JsonSlurper js = new JsonSlurper()
            int totalCount = 0
            int currentPage = 0
            float totalPages = 0
            List labels = []
            if (response) {
                Object responseJson = js.parseText(response)
    
                totalCount = responseJson.meta?.results?.total
                totalCount = (totalCount > OPENFDA_MAXSKIP) ? OPENFDA_MAXSKIP : totalCount
                
                if (responseJson.meta?.results?.skip && responseJson.meta?.results?.limit) {
                    currentPage =  Math.ceil(responseJson.meta?.results?.skip / responseJson.meta?.results?.limit)
                }
                
                if (totalCount && responseJson.meta?.results?.limit) {
                    totalPages = totalCount / responseJson.meta?.results?.limit
                } else if (totalCount) {
                    totalPages = 1
                } else {
                    totalPages = 0
                }
                
                if (responseJson.results) {
                    labels = responseJson.results.collect {
                        [ id : it.id,
                         title : getLabelTitle(it),
                         description : getLabelDescription(it)]
                    }
                }
            }
            return [totalCount : totalCount, totalPages : Math.ceil(totalPages).intValue(),
                    currentCount : labels.size(), currentPage : currentPage, labels : labels ]
        } catch (all) {
            throw new LabelServiceException('Error parsing response from openFDA api', all)
        }
    }

    /**
     * Extract the label title from label json
     * @param labelJson json representation of the label record
     * @return selected title of the label
     */
    private getLabelTitle(Map labelJson) {
        if (labelJson.openfda.brand_name) {
            return capitalize(labelJson.openfda.brand_name[0])
        } else if (labelJson.openfda?.generic_name) {
            return capitalize(labelJson.openfda.generic_name[0])
        } else if (labelJson.openfda.substance_name) {
            return capitalize(labelJson.openfda.substance_name[0])
        }
        labelJson.id
    }
    
    private String capitalize(String str) {
        str?.toLowerCase().tokenize()*.capitalize().join(SPACE_CHAR)
    }
    
    /**
     * Extract label description from label json
     * @param labelJson json representation of the label record
     * @return selected description of the label
     */
    private getLabelDescription(Map labelJson) {
        String result
        if (labelJson.description) {
            result = sanitizeLabelValue(['description'], labelJson.description[0])
        } else if (labelJson.package_label_principal_display_panel) {
             result = sanitizeLabelValue(['principal_display_panel', 'package/label principal display panel',
                  'package_label_principal_display_panel'], labelJson.package_label_principal_display_panel[0])
        } else { 
            result = labelJson.id
        }
        result
    }
    
    /**
     * Iterate through the label attributes and sanitize the attribute values 
     * @param labelAttrs map of attribute values
     * @return the sanitized label attribute map
     */
    private Map sanitizeLabelAttributeValues(Map labelAttrs) {
        labelAttrs.each { name, value ->
            if (value in String) {
                labelAttrs[name] = sanitizeLabelValue([name], value)
            } else if (value in List) {
                List newListVal = []
                value.each { val ->
                    if (val in String) {
                        newListVal += sanitizeLabelValue([name], val)
                    } else {
                        newListVal += val
                    }
                }
                labelAttrs[name] = newListVal
            } else if (value in Map) {
                labelAttrs[name]  = sanitizeLabelAttributeValues(value)
            }
        }
        labelAttrs
    }
    
    /**
     * Sanitize label attribute value
     *  - Remove the attribute name from the beginning of the attribute value 
     * @param attrNames list of attribute name to search for 
     * @param attrVal attribute value
     * @return sanitized attribute value
     */
    private String sanitizeLabelValue(List attrNames, String attrVal) {
        String result = attrVal
        attrNames.each { attrName -> 
            if (attrName && attrVal) {
                List prefixes = [attrName, 
                                attrName.replaceAll(UNDERSCORE_CHAR, SPACE_CHAR), 
                                attrName.replaceAll(UNDERSCORE_CHAR, SPACE_CHAR).replaceAll('and', '&')
                                ]
                prefixes.each { prefix -> 
                    if (attrVal.toLowerCase().startsWith(prefix)) {
                        if (attrVal.length() > prefix.length() + 1) {
                            result = attrVal[prefix.length() + 1 .. -1]
                        } else {
                            result = ''
                        } 
                    }
                }
            }
        }
        result
    }
}
