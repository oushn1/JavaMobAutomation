import io.appium.java_client.AppiumDriver;
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
import java.util.ArrayList;
import java.util.List;

public class FirstTest
{
    private AppiumDriver driver;
    public int timeout = 5;

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

    public boolean ElementHasText(By by, String expected_text, String error_message) {
        WebElement el = this.findElement(by, "couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    };

    public boolean ElementInElementHasText(By by1, By by2, String expected_text, String error_message) {
        WebElement el = this.findElementInElement(by1, by2,"couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    };

    @Test
    public void test3() throws InterruptedException {
        String word_to_search = "docker";
        By locator_search = By.id("org.wikipedia:id/search_container");
        By locator_clear_search = By.id("org.wikipedia:id/search_close_btn");
        By locator_search_result_items = By.id("org.wikipedia:id/page_list_item_title");

        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        search.sendKeys(word_to_search);
        List<WebElement> search_results = this.findElements(locator_search_result_items, "No search results found");

        Assert.assertFalse(search_results.isEmpty());
        List<WebElement> search_results_cleared = new ArrayList<>();
        findElement(locator_clear_search, "").click();
        try {
            search_results_cleared = this.findElements(locator_search_result_items, "");
        } catch (Exception exception) {};

        Assert.assertTrue(search_results_cleared.isEmpty());
    };
}