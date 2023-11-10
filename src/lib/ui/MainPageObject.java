package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class MainPageObject {

    protected AppiumDriver driver;
    protected int timeout = 5;

    public MainPageObject(AppiumDriver driver){
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
}
