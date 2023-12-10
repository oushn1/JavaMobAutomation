package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;


public class SearchPageObjectWeb extends MainPageObject {

    private static final By
            locator_search = By.id("searchInput"),
            locator_search_input = By.xpath("//input"),
            locator_search_result_items = By.xpath("//li[@data-id]"),
            locator_search_icon = By.className("minerva-icon minerva-icon--search-base20"),
            locator_clear_button = By.xpath("//div[@class='header-action']//button");

    public SearchPageObjectWeb(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Find an article")
    public void findArticle(String title) throws InterruptedException {
        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        List<WebElement> search_input = this.findElements(locator_search_input, "No search input found");
        search_input.get(3).sendKeys(title);
    }

    @Step("Find and open an article")
    public void findAndOpenArticle(String title) throws InterruptedException {
        WebElement search = this.findElement(locator_search, "No search field found");
        search.click();
        List<WebElement> search_input = this.findElements(locator_search_input, "No search input found");
        search_input.get(3).sendKeys(title);
        List<WebElement> search_results = this.findElements(locator_search_result_items, "No search results found");
        search_results.get(0).click();
    }

    public List<WebElement> getSearchResults() {
        List<WebElement> search_results = new ArrayList<WebElement>();
        try {
            search_results = this.findElements(locator_search_result_items, "No search results found");
        } catch (Exception ignored) {}
        return search_results;
    }

    @Step("Clear the search field")
    public void clearSearch() throws InterruptedException {
        WebElement clear_button = this.findElement(locator_clear_button,"Clear button not found");
        clear_button.click();
        Thread.sleep(2000);
    }
}
