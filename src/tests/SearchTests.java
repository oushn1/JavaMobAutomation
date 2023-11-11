package tests;

import lib.CoreTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    protected void setUp() throws Exception
    {
        super.setUp();
        this.beforeTests();
    }

    public void beforeTests()
    {
        WebElement skip_button = this.MainPageObject.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "");
        skip_button.click();
    }

    public void testSearch() throws InterruptedException {
        String word_to_search = "docker";

        SearchPageObject.findArticle(word_to_search);
        List<WebElement> search_results = SearchPageObject.getSearchResults();

        assertFalse(search_results.isEmpty());

        SearchPageObject.clearSearch();
        List<WebElement> search_results_cleared = SearchPageObject.getSearchResults();

        assertTrue(search_results_cleared.isEmpty());
    }

    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        SearchPageObject.findAndOpenArticle(text1);
        assertTrue(ArticlePageObject.isTitlePresent(text1));
    }
}
