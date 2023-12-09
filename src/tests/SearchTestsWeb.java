package tests;

import lib.CoreTestCase;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchTestsWeb extends CoreTestCase {

    protected void setUp() throws Exception
    {
        super.setUp();
        this.beforeTests();
    }


    public void testSearch() throws InterruptedException {
        String word_to_search = "docker";

        SearchPageObjectWeb.findArticle(word_to_search);
        List<WebElement> search_results = SearchPageObjectWeb.getSearchResults();

        assertFalse(search_results.isEmpty());

        SearchPageObjectWeb.clearSearch();
        List<WebElement> search_results_cleared = SearchPageObjectWeb.getSearchResults();

        assertTrue(search_results_cleared.isEmpty());
    }

    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        SearchPageObjectWeb.findAndOpenArticle(text1);

        assertTrue(ArticlePageObject.isTitlePresent(text1));
    }
}
