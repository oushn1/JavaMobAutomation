package lib;

import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import lib.ui.ArticlePageObject;
import lib.ui.ArticlePageObjectWeb;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.SearchPageObjectWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;
    public ArticlePageObject ArticlePageObject;

    public SearchPageObjectWeb SearchPageObjectWeb;
    public ArticlePageObjectWeb ArticlePageObjectWeb;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObjectWeb = new SearchPageObjectWeb(driver);
        ArticlePageObjectWeb = new ArticlePageObjectWeb(driver);

        this.openTestWebPage();
        this.logIn();
        this.beforeTests();
    };

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.setUp();
    }

    protected void openTestWebPage()
    {
        if(Platform.getInstance().isMW())
        {
            driver.get("http://en.m.wikipedia.org");
        }
    }

    public void logIn()
    {
        if(Platform.getInstance().isMW()){
            By locator_menu = By.xpath("//label[@for='main-menu-input']");
            By locator_menu_login = By.id("p-personal");
            By locator_user = By.id("wpName1");
            By locator_password = By.id("wpPassword1");
            By locator_ok = By.id("wpLoginAttempt");

            WebElement menu = this.MainPageObject.findElement(locator_menu, "");
            menu.click();
            WebElement menu_login = this.MainPageObject.findElement(locator_menu_login, "");
            menu_login.click();
            WebElement user = this.MainPageObject.findElement(locator_user, "");
            user.sendKeys("Garbuzovromang");
            WebElement password = this.MainPageObject.findElement(locator_password, "");
            password.sendKeys("1234");
            WebElement ok = this.MainPageObject.findElement(locator_ok, "");
            ok.click();

            this.openTestWebPage();
        }
    }

    public void beforeTests()
    {
        if(Platform.getInstance().isAndroid()){
        try{
            WebElement skip_button = this.MainPageObject.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "");
            skip_button.click();}
        catch (Exception ignored) {}
        }
    }

}