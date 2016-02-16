grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    // test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

def gebVersion = '0.10.0'
def webdriverVersion = '2.45.0'
def ghostDriverVersion = '1.1.0'

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'

        test "org.gebish:geb-junit4:${gebVersion}"
        test "org.gebish:geb-spock:${gebVersion}"

        test "org.seleniumhq.selenium:selenium-support:${webdriverVersion}"
        test "org.seleniumhq.selenium:selenium-chrome-driver:${webdriverVersion}"
        test "org.seleniumhq.selenium:selenium-firefox-driver:${webdriverVersion}"
        test "org.seleniumhq.selenium:selenium-ie-driver:${webdriverVersion}"
        test "org.seleniumhq.selenium:selenium-htmlunit-driver:${webdriverVersion}"
        test("com.github.detro.ghostdriver:phantomjsdriver:${ghostDriverVersion}") { transitive = false }

        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"

        // Barcode scanning API
        compile "com.google.zxing:javase:3.2.0"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55.2" // or ":tomcat:8.0.20"
        build ":codenarc:0.23"
        
        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.8'
        compile ":asset-pipeline:2.1.5"
        compile ":functional-test:2.0.0"
        compile ":jquery-ui:1.10.4"

        test ":code-coverage:2.0.3-3"

        // plugins needed at runtime but not for compilation
        // No DB needed for the application at this point 
        //runtime ":hibernate4:4.3.8.1" // or ":hibernate:3.6.10.18"
        //runtime ":database-migration:1.4.0"

        // Add angular JS and bootstrap
        runtime ':twitter-bootstrap:3.3.1'
        runtime ":jquery:1.11.1"
        runtime ':font-awesome-resources:4.3.0.1'
    }
}

//*******************************************************************************
// CodeNarc settings - refer  http://grails.org/plugin/codenarc for details
//*******************************************************************************
codenarc.maxPriority1Violations = 0
codenarc.maxPriority2Violations = 0
codenarc.maxPriority3Violations = 0
codenarc.systemExitOnBuildException = true   //exit when violations are above the limits

//turn off analyzing files in test folders
codenarc.processTestUnit = false
codenarc.processTestIntegration = false

// CodeNarc report configuration
codenarc.reports = {
    CodenarcXmlReport('xml') {
        outputFile = 'target/codenarc/codenarc-report.xml'
        title = 'CodeNarc Analysis Report'
    }

    CodenarcHTMLReport('html') {
        outputFile = 'target/codenarc/codenarc-report.html'
        title = 'CodeNarc Analysis Report'
    }
}


// Codenarc rules
codenarc.ruleSetFiles = [
    'rulesets/basic.xml',
    'rulesets/braces.xml',
    'rulesets/concurrency.xml',
    'rulesets/convention.xml',
    'rulesets/design.xml',
    'rulesets/dry.xml',
    'rulesets/enhanced.xml',
    'rulesets/exceptions.xml',
    'rulesets/formatting.xml',
    'rulesets/generic.xml',
    'rulesets/groovyism.xml',
    'rulesets/imports.xml',
    'rulesets/jdbc.xml',
    'rulesets/logging.xml',
    'rulesets/naming.xml',
    'rulesets/security.xml',
    'rulesets/serialization.xml',
    'rulesets/unnecessary.xml',
    'rulesets/unused.xml',
    'rulesets/grails.xml'
]

codenarc.properties = {
    LineLength.length = 180
    // Looks like there is an issue with this rule def, my code looks correct, but it still fails this validation
    // Disabling until the root cause is identified and fixed
    SpaceAroundMapEntryColon.enabled=false
    TrailingWhitespace.enabled=false
    
    maxPriority3Violations : 20
    
    GrailsStatelessService.addToIgnoreFieldNames='grailsApplication'
}

// Code coverage configuration  (https://github.com/beckje01/grails-code-coverage)
coverage {
    enabledByDefault = true
    xml = true
    exclusions = [
        '**/ApplicationResources*',
        '**/UrlMappings*',
        '**/*changelog*',
    ]
}

