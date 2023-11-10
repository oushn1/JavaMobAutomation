package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final By
            locator_search = By.id("org.wikipedia:id/search_container"),
            locator_clear_search = By.id("org.wikipedia:id/search_close_btn"),
            locator_search_result_items = By.id("org.wikipedia:id/page_list_item_title");

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void findArticle(String title)
    {
        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        search.sendKeys(title);
    }

    public void findAndOpenArticle(String title)
    {
        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        search.sendKeys(title);
        List<WebElement> search_results = this.findElements(locator_search_result_items, "No search results found");
        search_results.get(0).click();
    }

    public List<WebElement> getSearchResults() {
        List<WebElement> search_results;
        try {
            search_results = this.findElements(locator_search_result_items, "No search results found");
        } catch (Exception exception) {
            search_results = null;
        }
        return search_results;
    }

    public void clearSearch()
    {
        WebElement clear_button = this.findElement(locator_clear_search,"");
        clear_button.click();
    }
}
