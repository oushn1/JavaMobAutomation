package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject{

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private final By
        locator_save = By.id("Save"),
        locator_save_to_list = By.id("org.wikipedia:id/snackbar_action"),
        locator_readings_lists = By.id("org.wikipedia:id/item_title_container");

    private static By getLocatorByTextBySubstring(String ss)
    {
        return By.xpath("//*[@text='" + ss + "']");
    }

    private static By getLocatorByNameBySubstring(String substring)
    {
        return By.name(substring);
    }

    public void saveArticleToReadingList()
    {
        WebElement save = this.findElement(locator_save, "Save button not found");
        save.click();
        WebElement save_to_list = this.findElement(locator_save_to_list, "Save to list button not found");
        save_to_list.click();
        List<WebElement> lists = this.findElements(locator_readings_lists, "");
        lists.get(0).click();
    }

    public void removeArticleFromReadingList(String article_title, String list_title)
    {
        WebElement reading_list = this.findElement(getLocatorByTextBySubstring(list_title), "Reading list not found");
        reading_list.click();
        WebElement article = this.findElement(getLocatorByTextBySubstring(article_title), "Article not found");
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
        WebElement article = this.findElement(getLocatorByTextBySubstring(article_title), "Article not found");
        article.click();
    }

    public boolean isTitlePresent(String title_s)
    {
        WebElement title = driver.findElement(getLocatorByNameBySubstring(title_s));
        return true;
    }
}
