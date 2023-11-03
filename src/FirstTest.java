import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest
{
    private AppiumDriver driver;
    public int timeout = 10;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 5");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    };

    @Before
    public void beforeTests()
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("org.wikipedia:id/fragment_onboarding_skip_button"))).click();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    public WebElement findElement(By by, String message)
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    };

    public List<WebElement> findElements(By by, String message)
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    };

    public WebElement findElementInElement(By by1, By by2, String message)
    {
        WebDriverWait wait = new WebDriverWait(driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by1)).findElement(by2);
    };

    public boolean elementHasText(By by, String expected_text, String error_message) {
        WebElement el = this.findElement(by, "couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    };

    public boolean elementInElementHasText(By by1, By by2, String expected_text, String error_message) {
        WebElement el = this.findElementInElement(by1, by2,"couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    };


    public void findAndOpenArticle(String title)
    {
        By locator_search = By.id("org.wikipedia:id/search_container");
        By locator_search_result_items = By.id("org.wikipedia:id/page_list_item_title");

        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        search.sendKeys(title);
        List<WebElement> search_results = this.findElements(locator_search_result_items, "No search results found");
        search_results.get(0).click();
    }

    public void saveArticleToReadingList()
    {
        By locator_save = By.id("Save");
        By locator_save_to_list = By.id("org.wikipedia:id/snackbar_action");
        By locator_readings_lists = By.id("org.wikipedia:id/item_title_container");

        WebElement save = this.findElement(locator_save, "Save button not found");
        save.click();
        WebElement save_to_list = this.findElement(locator_save_to_list, "Save to list button not found");
        save_to_list.click();
        List<WebElement> lists = this.findElements(locator_readings_lists, "");
        lists.get(0).click();
    }

    public void removeArticleFromReadingList(String article_title, String list_title)
    {
        By locator_list = By.xpath("//*[@text='" + list_title + "']");
        By locator_article = By.xpath("//*[@text='" + article_title + "']");

        WebElement reading_list = this.findElement(locator_list, "Reading list not found");
        reading_list.click();
        WebElement article = this.findElement(locator_article, "Article not found");
        int x = article.getLocation().getX() + 10;
        int y = article.getLocation().getY() + 10;

        TouchAction swipe = new TouchAction(driver);
        swipe
                .press(x+800, y)
                .waitAction(200)
                .moveTo(x+100, y)
                .release()
                .waitAction(200)
                .perform();
    }

    public void openSavedArticle(String article_title)
    {
        By locator_article = By.xpath("//*[@text='" + article_title + "']");
        WebElement article = this.findElement(locator_article, "Article not found");
        article.click();
    }

    public void goHome()
    {
        By locator_options = By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu");
        By locator_option_explore = By.id("org.wikipedia:id/page_explore");

        WebElement options = this.findElement(locator_options, "");
        options.click();
        WebElement explore = this.findElement(locator_option_explore, "");
        explore.click();

    }

    public void goSaved()
    {
        By locator_saved = By.id("org.wikipedia:id/nav_tab_reading_lists");

        WebElement saved = this.findElement(locator_saved, "Save button not found");
        saved.click();
    }

    public boolean isTitlePresent(String title_s)
    {
        By locator_title = By.name(title_s);
        WebElement title = driver.findElement(locator_title);
        return true;
    }

    @Test
    public void test42() throws InterruptedException {
        String text1 = "Plutonium";
        this.findAndOpenArticle(text1);
        Assert.assertTrue(this.isTitlePresent(text1));
    };
}