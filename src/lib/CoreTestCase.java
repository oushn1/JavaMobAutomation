package lib;

import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;
    public ArticlePageObject ArticlePageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 5");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
    };

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.setUp();
    }

}