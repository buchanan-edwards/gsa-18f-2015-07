package com.semanticbits.label.service

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BarcodeService)
class BarcodeServiceSpec extends Specification {

    def barcodeService
    
    void "test scan empty barcode"() {
        when:
        barcodeService.scanBarcode(null)
        then:
        LabelServiceException e = thrown()
        e.message == 'Image file is empty'
    }

    void "test scan barcode"() {
        given:
        byte [] image1 =  new File ('test/integration/resources/barcode1.png').bytes
        byte [] image2 =  new File ('test/integration/resources/barcode2.png').bytes
        byte [] image3 =  new File ('test/integration/resources/barcode3.png').bytes
        when:
        def barCode1 = barcodeService.scanBarcode(image1)
        def barCode2 = barcodeService.scanBarcode(image2)
        def barCode3 = barcodeService.scanBarcode(image3)
        then:
        barCode1 == '123456'
        barCode2 == '123456789104'
        barCode3 == '811204012344'
    }
    
    void "test scan barcode no filename"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode1.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image, 'barcode.png')
        then:
        barCode == '123456'
    }
    
    void "test scan barcode on photo 1"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode-photo1.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '043000181706'
        
    }
    
     void "test scan barcode on photo 2"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode-photo2.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '083275099870'
        
    }
     
    void "test scan drug label image1"() {
         given:
         byte [] image =  new File ('test/integration/resources/druglabels/image1.jpg').bytes
         when:
         def barCode = barcodeService.scanBarcode(image)
         then:
         barCode == '300450123046'
         
     }
    
    void "test scan drug label image2"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/image2.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '360505082919'
        
    }
   
    void "test scan drug label image3"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/image3.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '323900014527'        
    }
    
    void "test scan drug label image4"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/image4.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '300450496607'
        
    }
    
    void "test scan drug label image5"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/image5.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '050428375464'
    }
    
    void "test scan drug label ACETAMINOPHEN"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/Junior Strength Pain Reliever Grape.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '0015127022989'
    }
    
    void "test scan drug label Child ACCUDIAL"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/ChildACCUDIAL.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '0345014000371'
    }
    
    void "test scan drug label WG ColdCough"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/Care One Cold Multi Symptom.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '0341520318382'
    }
    
    void "test scan drug label Allergy and Sinus"() {
        given:
        byte [] image =  new File ('test/integration/resources/druglabels/Pain Reliever PM.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '0070253962127'
    }
}
