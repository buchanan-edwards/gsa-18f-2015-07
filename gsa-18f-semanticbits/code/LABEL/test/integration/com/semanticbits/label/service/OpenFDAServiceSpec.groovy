package com.semanticbits.label.service

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(OpenFDAService)
class OpenFDAServiceSpec extends Specification {

    def openFDAService
    def grailsApplication
    
    void "invoke openFDA"() {
        when:
        def resp = openFDAService.invoke([search : 'motrin'])
        then:
        resp != null 
    }
    
    void "invoke openFDA with no search param"() {
        when:
        def resp = openFDAService.invoke([:])
        then:
        resp != null
    }
    
    void "invoke openFDA with null search param"() {
        when:
        def resp = openFDAService.invoke()
        then:
        resp != null
    }
    
    void "invoke openFDA with empty search param"() {
        when:
        def resp = openFDAService.invoke([search : '' ])
        then:
        resp != null
    }
    
    void "invoke openFDA with skip and limit"() {
        when:
        def resp = openFDAService.invoke([search : 'motrin', limit : 10, skip : 1])
        then:
        resp != null
    }
    
    void "invoke openFDA with openFDA API url not configured "() {
        given:
        def goodURL = grailsApplication.config.openFDA.API.url
        grailsApplication.config.openFDA.API.url=''
        when:
        def resp = openFDAService.invoke([search : 'motrin', limit : 10, skip : 1])
        then:
        LabelServiceException e = thrown()
        e.message == 'openFDA.API.url property not configured, unable to invoke openFDA API'
        cleanup:
        grailsApplication.config.openFDA.API.url=goodURL
    }
    
    void "invoke openFDA with openFDA API key not specified "() {
        given:
        def goodKey = grailsApplication.config.openFDA.API.key
        grailsApplication.config.openFDA.API.key = ''
        when:
        def resp = openFDAService.invoke([search : 'motrin', limit : 10, skip : 1])
        then:
        resp != null
        cleanup:
        grailsApplication.config.openFDA.API.key = goodKey
    }
    
    void "invoke openFDA with openFDA API invalid key "() {
        given:
        def goodKey = grailsApplication.config.openFDA.API.key
        grailsApplication.config.openFDA.API.key = 'invalid'
        when:
        def resp = openFDAService.invoke([search : 'motrin', limit : 10, skip : 1])
        then:
        LabelServiceException e = thrown()
        e.message == 'Error invoking the openFDA API'
        cleanup:
        grailsApplication.config.openFDA.API.key = goodKey
    }
}
