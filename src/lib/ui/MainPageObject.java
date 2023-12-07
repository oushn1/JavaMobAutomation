package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class MainPageObject {

    protected RemoteWebDriver driver;
    protected int timeout = 5;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public WebElement findElement(By by, String message)
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(By by, String message)
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement findElementInElement(By by1, By by2, String message)
    {
        WebDriverWait wait = new WebDriverWait(driver, this.timeout);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by1)).findElement(by2);
    }

    public boolean elementHasText(By by, String expected_text, String error_message) {
        WebElement el = this.findElement(by, "couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    }

    public boolean elementInElementHasText(By by1, By by2, String expected_text, String error_message) {
        WebElement el = this.findElementInElement(by1, by2,"couldn't find an element");
        String el_text = el.getText();
        return el_text.contains(expected_text);
    }

    public void goHome()
    {
        if(Platform.getInstance().isAndroid())
        {
            By locator_options = By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu");
            By locator_option_explore = By.id("org.wikipedia:id/page_explore");

            WebElement options = this.findElement(locator_options, "");
            options.click();
            WebElement explore = this.findElement(locator_option_explore, "");
            explore.click();
        }

        if (Platform.getInstance().isMW()){
                driver.get("http://en.m.wikipedia.org");
            }
    }

    public void goSaved()
    {
        By locator_saved = By.xpath("//span[text()='Watchlist']");
        By lcoator_menu = By.id("mw-mf-main-menu-button");

        WebElement menu = this.findElement(lcoator_menu, "Save button not found");
        menu.click();
        WebElement saved = this.findElement(locator_saved, "Save button not found");
        saved.click();
    }
}
