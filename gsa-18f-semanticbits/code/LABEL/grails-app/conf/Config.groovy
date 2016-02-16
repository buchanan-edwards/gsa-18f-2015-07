import grails.util.Environment;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;

// locations to search for config files that get merged into the main config;

// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// Load app config from external config file
def configFolder = "${userHome}/.${appName}"
def extConfig = "${configFolder}/${appName}-config.groovy"
if(new File("${extConfig}").exists()) {
    println("Loading ${appName} configuration from: ${extConfig}")
}else {
    println("External configuration '${extConfig}' not found, LABEL application can not start")
}
grails.config.locations = [extConfig]

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html', 'application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json', 'application/hal+xml'],
    xml:           ['text/xml', 'application/xml']]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

def log4jFileName = "${configFolder}/logs/${appName}.log"
applicationLogLevel = "ALL"

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    appenders {
        // DailyRollingFileAppender Options   http://www.vipan.com/htdocs/log4jhelp.html
        //Threshold=WARN: Appender will not loaqg any messages with priority lower than the one specified here even if the category's priority is set lower.
        //This is useful to cut down the number of messages, for example, in a file log while displaying all messages on the console.
        appender new DailyRollingFileAppender(name: 'LABELLogfile',
            threshold: Level.toLevel(config.applicationLogLevel),
            file: log4jFileName,
            datePattern: "'.'yyyy-MM-dd",   //Rollover at midnight each day.
            layout: pattern(conversionPattern: '%d{yyyy-MM-dd/HH:mm:ss.SSS} [%t] %x %-5p %c{2} - %m%n')
            )

        appender new ConsoleAppender(name: 'console',
            threshold: Level.toLevel(config.applicationLogLevel),
            layout: pattern(conversionPattern: '%d{yyyy-MM-dd/HH:mm:ss.SSS} [%t] %x %-5p %c{2} - %m%n')
            )
    }


    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
            
    debug 'com.semanticbits.label'

    List<String> loggers = []
    //This is to log errors in log file for all environments
    loggers.add('LABELLogfile')
    if (Environment.current in [Environment.DEVELOPMENT, Environment.TEST]) {
        loggers.add('console')
    }
    root {
        error loggers as String[]
        additivity = true
    }
}

// open FDA API properties, values given here are overridden by the values
// given in the externalized config file at $HOME/.LABEL/LABEL-config.groovy
openFDA.API.url='https://api.fda.gov/drug/label.json'
openFDA.API.key=''
itemsPerPage=10


labels.map = [
              "IDANDVERSION": ["set_id", "id", "version", "effective_time"],
              "ABUSEANDOVERDOSE": ["drug_abuse_and_dependence", "controlled_substance", "abuse", "dependence", "overdosage"],
              "ADVERSEEFFECTS": ["adverse_reactions", "drug_interactions", "drug_and_or_laboratory_test_interactions"],
              "CLINICALPHAR": ["clinical_pharmacology", "mechanism_of_action", "pharmacodynamics", "pharmacokinetics"],
              "INDICATIONS": ["indications_and_usage", "contraindications", "dosage_and_administration", "dosage_forms_and_strengths", "purpose", "description", "active_ingredient", "inactive_ingredient", "spl_product_data_elements"],
              "PATIENTINFO": ["spl_patient_package_insert", "information_for_patients", "information_for_owners_or_caregivers", "instructions_for_use", "ask_doctor", "ask_doctor_or_pharmacist", "do_not_use", "keep_out_of_reach_of_children", "other_safety_information", "questions", "stop_use", "when_using"],
              "SPECIALPOPULATION": ["use_in_specific_populations", "pregnancy", "teratogenic_effects", "nonteratogenic_effects", "labor_and_delivery", "nursing_mothers", "pregnancy_or_breast_feeding", "pediatric_use", "geriatric_use"],
              "TOXILOGY": ["nonclinical_toxicology", "carcinogenesis_and_mutagenesis_and_impairment_of_fertility", "animal_pharmacology_and_or_toxicology"],
              "REFERENCE": ["clinical_studies", "references"],
              "SUPPLYSTORAGE": ["how_supplied", "storage_and_handling", "safe_handling_warning"],
              "WARNINGS": ["boxed_warning", "warnings_and_precautions", "user_safety_warnings", "precautions", "warnings", "general_precautions"],
              "OTHER": ["laboratory_tests", "recent_major_changes", "microbiology", "package_label_principal_display_panel", "spl_unclassified_section"],
              "OPENFDA": ["application_number", "brand_name", "dosage_form", "generic_name", "manufacturer_name", "is_original_packager", "product_ndc", "product_type", "route", "substance_name", "spl_id",
                            "spl_set_id", "pharm_class_moa", "pharm_class_cs", "pharm_class_pe", "pharm_class_epc", "upc", "unii", "rxcui"],
            ]



// Barcode work directory for saving image files and barcode files
barcode.workdir="${configFolder}/barcode"
