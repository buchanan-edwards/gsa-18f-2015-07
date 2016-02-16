import org.codehaus.groovy.grails.commons.GrailsApplication

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        // Initialize barcode work directory
        File barCodeDir = new File(grailsApplication.config.barcode.workdir + '/')
        if (!barCodeDir.exists()) {
            log.info("Creating barcode work folder ${barCodeDir.absolutePath}")
            barCodeDir.mkdirs()
        }
    }

    def destroy = {
    }
}
