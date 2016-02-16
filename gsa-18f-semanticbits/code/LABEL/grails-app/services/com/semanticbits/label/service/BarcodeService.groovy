package com.semanticbits.label.service

import org.codehaus.groovy.grails.commons.GrailsApplication

import com.google.zxing.client.j2se.CommandLineRunner

/**
 * Scans a barcode image and extract the barcode
 * @author gopal
 *
 */

@SuppressWarnings(['JavaIoPackageAccess'])
class BarcodeService {

    GrailsApplication grailsApplication

    /**
     * Scan barcode from the image
     * @param image image data
     * @return the barcode string contained in the image
     */
    String scanBarcode(byte[] image, String imageName = null) throws LabelServiceException {
        if (!image) {
            throw new LabelServiceException('Image file is empty')
        }
        File imageFile
        try {
            // This is the only invokable public method on the ZXing API!
            imageFile = saveImage(image, imageName)
            CommandLineRunner.main([imageFile.absolutePath, '--try_harder', '--dump_results'] as String [])
            String result = readBarcodeOutput(imageFile)
            result
        } finally {
            if (imageFile) {
                cleanupTempfiles(imageFile)
            }
        }
    }

    /**
     * Save the image to work dir and return the filename
     * @param image image data
     * @param imageName imageName
     * @return saved image file
     */
    private File saveImage(byte[] image, String imageName) {
        File imageFile = new File(grailsApplication.config.barcode.workdir, imageName ?: 
                                                        "${System.currentTimeMillis()}.jpg")
        imageFile.bytes = image
        imageFile
    }

    /**
     * Read the barcode output file and return the contents
     * @param imageName
     * @return
     */
    private String readBarcodeOutput(File imageFile) throws LabelServiceException {
        File outFile = new File(getBarcodeOutFilename(imageFile))
        if (outFile.exists()) {
            return outFile.text.trim()
        }
        throw new LabelServiceException("Barcode scanning failed, output file ${outFile.absolutePath} not found")
    }

    /**
     * Get barcode output file name for the given image file
     * @param imageFile image file
     * @return
     */
    private String getBarcodeOutFilename(File imageFile) {
        String outFileName = imageFile.absolutePath
        int pos = outFileName.lastIndexOf('.')
        outFileName = pos ? (outFileName[0..pos - 1] + '.txt') : outFileName
    }

    /**
     * Cleanup temp files created for scanning
     * @param imageName
     * @return
     */
    private cleanupTempfiles(File imageFile) {
        imageFile.delete()
        new File(getBarcodeOutFilename(imageFile)).delete()
    }
}
