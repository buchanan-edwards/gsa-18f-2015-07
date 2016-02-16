package com.semanticbits.label.controller

import com.semanticbits.label.service.BarcodeService
import com.semanticbits.label.service.LabelServiceException
import com.semanticbits.label.service.SearchService
import grails.converters.JSON
import grails.util.Holders
import grails.web.JSONBuilder
import org.springframework.web.multipart.MultipartFile

/**
 * User: Janakiram Gollapudi
 * Date: 6/22/15
 */
class SearchController {
    SearchService searchService
    BarcodeService barcodeService

    /**
     * To display label home page(which is search 'label' page)
     * @return home page
     */
    Object index() {
      render(view:'index')
    }

    /**
     * When user clicks on search button this method gets called
     * @return search results view
     */
    Object textSearchView() {
        String term = params.term
        render(view:'results', model:[term:term])
    }

    /**
     * Processes a bar code and returns value
     */
    JSON processBarCodeImage() {
        String results = []
        if (request) {
            for (filename in request.fileNames) {

                MultipartFile file = request.getFile(filename)

                String scannedCode = barcodeService.scanBarcode(file.bytes)

                JSONBuilder jSON = new JSONBuilder()
                JSON json = jSON.build {
                    size = file.size
                    code = scannedCode
                }

                results = json.toString()
            }
        }

        render results
    }

/**
 * This method gets called on every search button click and on every pagination
 * @return labels information based on search string as JSON
 */
    JSON textSearch() {
        //User entered term
        String term = params.term
        //To capture labels information
        List<Object> labels = []
        //To capture total labels
        int iTotalRecords = 0
        //To capture total labels that we are showing
        int iTotalDisplayRecords = 0
        int currentPage = 0
        int itemsPerPage = grailsApplication.config.itemsPerPage
        int start  = params.page ? ((params.int('page')) * itemsPerPage) : params.int('start')
        //If term provided then search by term and return the results
        try {
                //Get the current page from start and number of records per page
                currentPage = (start / (grailsApplication.config.itemsPerPage))
                //Get the results using search term
                log.info "Searching for labels with given string: ${term}"
                Map<String, Object> searchResults = searchService.search(term, currentPage)
                log.info "Found ${searchResults.totalCount} labels"
                iTotalRecords = searchResults.totalCount ?: 0
                iTotalDisplayRecords = searchResults.totalCount ?: 0
                //loop through labels that are found and construct view to show results
                searchResults.labels.each { label ->
                    labels << [
                            labelDetails:label
                    ]
                }
                log.info "Showing results of page ${currentPage}"
        }
        catch (LabelServiceException e) {
            log.error("Exception occurred while searching term ${params.term}: ${e}")
            render([currentPage: currentPage, iTotalRecords:iTotalRecords,
                    iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
        }

        //Otherwise return empty results
        render([currentPage: currentPage, iTotalRecords:iTotalRecords,
                iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
    }

    /**
     * The method returns details for a LABEL term
     */
    Object details() {
        Map termDetails = searchService.getLabelDetails(params.id)
        String backToSearchURL = createLink(action: 'textSearchView', params: [term: params.term, page: params.page])
        render(view: 'view', model: [details: termDetails, title: params.title, backToSearchURL: backToSearchURL])
    }

    /**
     * Advanced search for label
     * This method gets called when user try to search labels began with '#'
     * @return results as JSON
     */
    JSON autocomplete() {
       String term = params.term
       //Get label fields map from config file
       Map<String, List<String>> labelMap = Holders.config.labels.map
       List<String> labelFields = []
       //Extract all label values from the map and put it in a list
       labelMap.each { key, value ->
           labelFields.addAll(value)
       }
       //Filter list by given string
       List<String> foundLabelFields = labelFields.findAll { it.startsWith(term[1..term.length() - 1]) }   //Remove first character and search string in list
       render foundLabelFields.sort() as JSON
    }
}
