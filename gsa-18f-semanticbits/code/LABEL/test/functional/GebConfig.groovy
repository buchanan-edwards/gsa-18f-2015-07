/**
 * User: Janakiram Gollapudi
 * Date: 6/23/15
 */
import org.openqa.selenium.firefox.FirefoxDriver

driver = {
    //set the firefox locale to 'en-us' since the tests expect english
    //see http://stackoverflow.com/questions/9822717 for more details
    def driverInstance = new FirefoxDriver()
    driverInstance
}

baseNavigatorWaiting = true
atCheckWaiting = true
