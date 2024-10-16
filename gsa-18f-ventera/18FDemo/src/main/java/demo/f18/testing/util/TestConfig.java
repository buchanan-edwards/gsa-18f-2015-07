package demo.f18.testing.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


@Listeners(demo.f18.testing.util.TestConfig.class)
public class TestConfig extends TestListenerAdapter implements ITestListener{

	protected Logger logger = Logger.getLogger( this.getClass() );

	protected static final String ROOT_DIR = System.getProperty("user.dir");
	protected static final String PATH_SEPARATOR = System.getProperty("file.separator");
	protected static final String SCREENSHOT_FOLDER = "target"+ PATH_SEPARATOR + "screenshots" + PATH_SEPARATOR;
	protected static final String SCREENSHOT_FORMAT = ".png";
	protected WebDriver driver;
	private String siteUrl;
	private String seleniumHub;
	private String seleniumHubPort;
	private String targetBrowser;
	private String currentTest;	
	private String exportDirectoryPath;
	private String OSVersion;
	

	/**
	 * Fetches suite-configuration.
	 *  
	 * @param testContext
	 */
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) {
		siteUrl = PropertyLoader.loadProperty("site.url");		
		seleniumHub = PropertyLoader.loadProperty("grid2.hub");
		targetBrowser = PropertyLoader.loadProperty("browser.name");
		OSVersion = PropertyLoader.loadProperty("OSVersion");	
		logger.debug ("Website Url : " + siteUrl + "Selenium Hub : " +seleniumHub + "Target Browser : " +targetBrowser + "OS Version : " + OSVersion);
	}


	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws IOException, InterruptedException {
		currentTest = method.getName(); // get Name of current test.
		logger.debug (" Name of current test : " + currentTest); 
		
		DesiredCapabilities capability = null;
		if (targetBrowser == null || targetBrowser.contains("firefox") || targetBrowser.contains("ff") ) { // firefox browser settings
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.max_chrome_script_run_time", "999");
			profile.setPreference("dom.max_script_run_time", "999");
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			//profile.setPreference("browser.download.dir", exportDirectoryPath );
			//profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/jobExport");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","*");
			profile.setPreference("extensions.update.enabled", false);
			profile.setPreference("app.update.enabled", false);
			profile.setPreference("app.update.auto", false);
			profile.setEnableNativeEvents(true);
			profile.setPreference("network.http.use-cache", false);	
			capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			capability.setCapability(FirefoxDriver.PROFILE, profile);			
			this.driver = new FirefoxDriver(capability);

		} else if (targetBrowser.contains("ie8")) { // IE8 browser settings
			capability = DesiredCapabilities.internetExplorer();
			capability.setPlatform(Platform.ANY);
			capability.setBrowserName("internet explorer");
			// capability.setVersion("8.0");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
			capability.setJavascriptEnabled(true);
			driver = new InternetExplorerDriver(capability);		

		} else if (targetBrowser.contains("chrome")) { // Chrome browser settings
			capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver64bit\\chromedriver.exe");
			// driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);			 
			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
			this.driver = new ChromeDriver(capability);

		} else if (targetBrowser.contains("ie")) { // IE 11 browser settings
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver32bit\\IEDriverServer.exe");	
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
			capability.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"");
			capability.setJavascriptEnabled(true);
			capability.setCapability("ignoreZoomSetting", true);
			this.driver = new InternetExplorerDriver(capability);

		} else if (targetBrowser.contains("safari")) { // Safari browser settins
			System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\SafariDriver.safariextz");
			// driver = new SafariDriver();
			SafariDriver profile = new SafariDriver();
			capability = DesiredCapabilities.safari();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			//capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY, profile);
			this.driver = new SafariDriver(capability);
		} else if (targetBrowser.contains("ghost")) { // Gosht driver browser setting for Windows					
			capability = DesiredCapabilities.phantomjs();
	        // This line is for Windows.  	
			//capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir")+"\\src\\main\\resources\\ghostDriver\\phantomjs.exe");
			//This line is for Unix 1
			capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/home/rharvey/phantomjs-ubuntu");
			
			this.driver = new PhantomJSDriver(capability);
		}
		
		/*else if (targetBrowser.contains("ghostU")) { // Gosht driver browser setting for UNIX 					
			capability = DesiredCapabilities.phantomjs();
	        // This line is for Windows.  	
			//capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir")+"\\src\\main\\resources\\ghostDriver\\phantomjs.exe");
			//This line is for Unix 
			capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "//usr//bin//phantomjs");
			
			this.driver = new PhantomJSDriver(capability);
		}*/
		
		
		/*System.out.println("Remote Grid : " + remote_grid );
			// Instantiate RemoteWebDriver

			for (int count=1; count<=3; count++){
				try{
					driver = new RemoteWebDriver(remote_grid, capability);
					System.out.println("Driver : " + driver );
				}catch(Exception e){
					System.out.println(e.getMessage());
					Thread.sleep(1000);
				}
			}*/

		this.driver.manage().window().maximize();	// Maximize browser window	
		this.driver.get(siteUrl);   // Open test url
	}


	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		System.out.println("Inside TestConfig  tearDown()");
		if (!result.isSuccess()) {
			try {
				//WebDriver returned = new Augmenter().augment(webDriver);
				if (driver != null) {
					File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					try {
						FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER +  result.getName() + SCREENSHOT_FORMAT)
						.getAbsoluteFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (ScreenshotException se) {
				se.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		if (driver != null) {
		//TODO; 	//driver.close();
			// driver.quit();
		}
	}

	/**
	 * Generates both a console log and a reporter log for given message
	 * 
	 * @param msg
	 * @param logLevel
	 */
	public void log(String msg) {
		LoggingUtil.trace(msg, logger);
		if(msg.endsWith(".")) {
			msg=msg.substring(0, msg.length()-1) + "|" ; 
		} else {
			msg=msg + "|"; 
		}
		System.out.println( FrameworkConstants.SPACES_THREE + "|" + msg );
		Reporter.log(msg); 
	}

	/**
	 * Prints given message to reporter output and to console
	 * 
	 * @param msg Messages to be printed
	 * 
	 * @param logLevel Log level/category
	 */
	public void log(String msg, String logLevel) {

		if(msg.endsWith(".")) {
			msg=msg.substring(0, msg.length()-1) + "|" ; 
		} else {
			msg=msg + "|"; 
		}

		System.out.println( FrameworkConstants.SPACES_THREE + "|" + msg );


		if(logLevel==FrameworkConstants.PAGE){

			Reporter.log("<br/><b><font color='DarkRed'>" + msg + "</font></b>");

		} else if(logLevel==FrameworkConstants.TEST){

			Reporter.log("<br/>" + FrameworkConstants.HTML_SPACE + "<font color='Green'>" + msg + "</font>");

		} else if(logLevel==FrameworkConstants.METHOD){ // add 3 space indentation

			Reporter.log("<br/>" + FrameworkConstants.HTML_SPACE_THREE + "<font color='Green'>" + msg + "</font>" );

		} else if(logLevel == FrameworkConstants.QUESTION){

			Reporter.log("<br/>" + FrameworkConstants.HTML_SPACE + "<i><font color='Yellow'>" + msg + "</font></i>");

		} else if(logLevel == FrameworkConstants.TO_DO_STEPS){

			Reporter.log("<br/>" + FrameworkConstants.HTML_SPACE + "TODO: <i><font color='Megenta'>" + msg + "</font></i>");

		} else if(logLevel == FrameworkConstants.ASSERTS){

			Reporter.log("<br/><b>" + FrameworkConstants.HTML_SPACE + "CHECKPOINT: <font color='Green'>" + msg + "</font></b>");

		} else if(logLevel == FrameworkConstants.TESTCASE){

			Reporter.log("<br/> <b>TESTCASE:<font color='Green'>" + msg + "</font></b>");

		} else if(logLevel == FrameworkConstants.PRE_CONDITION){

			Reporter.log("<br/> PRECONDITION:<b><font color='Megenta'>[" + msg + "]</font></b>");

		} else if(logLevel == FrameworkConstants.BUG){

			Reporter.log("<br/><font color='Red' style='background-color: yellow;'><b> BUG: [" + msg + "]</font> </b>");

		} else if(logLevel == FrameworkConstants.MANUAL_TESTING_NOTE){

			Reporter.log("<br/><font color='Black' style='background-color: yellow;'><b> Note For Manual Testers: </b>[" + msg + "]</font>");

		} else if(logLevel == FrameworkConstants.BUG_GIT_HUB_LINK){

			msg = msg.replace("|", FrameworkConstants.SPACE ) ;
			Reporter.log("<br/><font color='Blue' style='background-color: yellow;'><b> ISSUE_Link:  "
					+ "<a href='"+ msg + "'>"
					+ "[" + msg + "]"
					+ " </a></font> </b>");
			//throw new Error("Force-fail-manually. Check Bug description and Issue link in log");
		} else if(logLevel == FrameworkConstants.ERROR ){

			Reporter.log("<br/> <b> <font color='Red' style='background-color: white;'> [" + msg + "]</font> </b>");

		} else if(logLevel == FrameworkConstants.WARNING ){

			Reporter.log("<br/> <b> <font color='Yellow' style='background-color: gray;'> [" + msg + "]</font> </b>");

		} else if(logLevel == FrameworkConstants.FAILURE ){

			Reporter.log("<br/> <b> <font color='Red' size='3' > [" + msg + "]</font> </b>");

		}  

	}

	/**
	 * This method should be used to log test-steps 
	 * 
	 * @param step Test step description
	 */
	public final void step( String step ){
		log( step  , FrameworkConstants.METHOD );
	}

	/**
	 * This method should be used to log testcase description
	 * 
	 * @param testcase Testcase description
	 */
	public final void testcase( String testcase ){
		log( testcase  , FrameworkConstants.TESTCASE );
	}

	/**
	 * This method should be used to log test-description
	 *  
	 * @param test Test description
	 */
	public final void test( String test ){
		log( test  , FrameworkConstants.TEST );
	}

	/**
	 * This method should be used to log note-for-manual-testers
	 * 
	 * @param note Note for manual testers / automation testers
	 */
	public final void note( String note ){
		log( note  , FrameworkConstants.MANUAL_TESTING_NOTE );
	}

	/**
	 * This method when used, test will stop if the condition(actual and expected) does not meet
	 * And will add a log as well in test-report
	 * 
	 * @param successMessage Message to be printed if conditions met 
	 * 		  else print a failure message
	 * 
	 * @param actual Actual result
	 * 
	 * @param expected Expected result
	 */
	public final void fail( String successMessage , Object actual, Object expected ){
		try{
			Assert.assertEquals(actual, expected, FrameworkConstants.EXPECTED_MATCH_NOT_FOUND );
			log( successMessage , FrameworkConstants.ASSERTS );
		} catch ( Error e) {
			log( successMessage , FrameworkConstants.FAILURE );
			throw new Error(e);
		}
	}

	/**
	 * This method when used, test will not stop if the condition(actual and expected) does not meet
	 * And will add a provided log-message in test-report 
	 * 
	 * @param successMessage Message to be printed if conditions met 
	 *                       else print an error message
	 *                       
	 * @param actual Actual result
	 * 
	 * @param expected Expected result
	 */
	public final boolean error( String successMessage , Object actual, Object expected  ){
		try{
			Assert.assertEquals(actual, expected, FrameworkConstants.EXPECTED_MATCH_NOT_FOUND );
			log( successMessage , FrameworkConstants.ASSERTS );
			return false;
		} catch(Error e){
			log( successMessage + "<br/>ERROR: <br/>Expected[" + expected + "] <br/>Found[" + actual + "]", FrameworkConstants.ERROR );
			return true;
		}
	}

	/**
	 * This method when used, test will not stop if the condition(actual and expected) does not meet
	 * And will add a provided log-message in test-report
	 * 
	 * @param successMessage Message to be printed if conditions met 
	 *                       else print a warning message
	 *                       
	 * @param actual Actual result
	 * 
	 * @param expected Expected result
	 */
	public final boolean warn( String successMessage , Object actual, Object expected  ){
		try{
			Assert.assertEquals(actual, expected, FrameworkConstants.EXPECTED_MATCH_NOT_FOUND );
			log( successMessage , FrameworkConstants.ASSERTS );
			return false;
		} catch(Error e){
			log( successMessage + "<br/>ERROR: <br/>Expected[" + expected + "] <br/>Found[" + actual + "]", FrameworkConstants.WARNING );
			return true;
		}
	}


	@Override
	public void onTestStart(ITestResult result) {}

	@Override
	public void onTestSuccess(ITestResult result) {}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log( "Screenshot path:" + ROOT_DIR + PATH_SEPARATOR +  SCREENSHOT_FOLDER +  result.getName() + SCREENSHOT_FORMAT );
	}

	@Override
	public void onTestSkipped(ITestResult result) {}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	@Override
	public void onStart(ITestContext context) {}

	@Override
	public void onFinish(ITestContext context) {}	
}


