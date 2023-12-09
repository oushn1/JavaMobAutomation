package tests;

import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchTestsWeb extends CoreTestCase {

    public void setUp() throws Exception
    {
        super.setUp();
        this.beforeTests();
    }

    @Test
    public void testSearch() throws InterruptedException {
        String word_to_search = "docker";

        SearchPageObjectWeb.findArticle(word_to_search);
        List<WebElement> search_results = SearchPageObjectWeb.getSearchResults();

        Assert.assertFalse(search_results.isEmpty());

        SearchPageObjectWeb.clearSearch();
        List<WebElement> search_results_cleared = SearchPageObjectWeb.getSearchResults();

        Assert.assertTrue(search_results_cleared.isEmpty());
    }

    @Test
    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        SearchPageObjectWeb.findAndOpenArticle(text1);

        Assert.assertTrue(ArticlePageObject.isTitlePresent(text1));
    }
}
