package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ArticlePageObjectWeb extends MainPageObject{

    public ArticlePageObjectWeb(RemoteWebDriver driver)
    {
        super(driver);
    }

    private final By
        locator_save = By.id("page-actions-watch"),
        locator_save_to_list = By.id("org.wikipedia:id/snackbar_action"),
        locator_readings_lists = By.id("org.wikipedia:id/item_title_container");


    private static By getLocatorByTextBySubstring(String ss)
    {
        return By.xpath("//*[@text='" + ss + "']");
    }

    private static By getLocatorByTitle(String ss)
    {
        return By.xpath("//*[@title='" + ss + "']");
    }

    private static By getLocatorByNameBySubstring(String substring)
    {
        return By.name(substring);
    }

    @Step("Save an article to the reading list")
    public void saveArticleToReadingList()
    {
        WebElement save = this.findElement(locator_save, "Save button not found");
        save.click();
    }

    @Step("Remove an article from the reading list")
    public void removeArticleFromReadingList(String article_title, String list_title)
    {
        By locator_button = By.xpath("//a[@type='button']");
        WebElement remove_button = this.findElementInElement(getLocatorByTitle(article_title), locator_button, "");
        remove_button.click();
    }

    @Step("Open a saved article")
    public void openSavedArticle(String article_title)
    {
        WebElement article = this.findElement(getLocatorByTitle(article_title), "Article not found");
        article.click();
    }

    public boolean isTitlePresent(String title_s)
    {
        WebElement title = driver.findElement(getLocatorByNameBySubstring(title_s));
        return true;
    }
}
