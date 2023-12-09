package tests;

import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    public void setUp() throws Exception
    {
        super.setUp();
        this.beforeTests();
    }

    @Test
    public void testSearch() throws InterruptedException {
        String word_to_search = "docker";

        SearchPageObject.findArticle(word_to_search);
        List<WebElement> search_results = SearchPageObject.getSearchResults();

        Assert.assertFalse(search_results.isEmpty());

        SearchPageObject.clearSearch();
        List<WebElement> search_results_cleared = SearchPageObject.getSearchResults();

        Assert.assertTrue(search_results_cleared.isEmpty());
    }

    @Test
    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        SearchPageObject.findAndOpenArticle(text1);
        Assert.assertTrue(ArticlePageObject.isTitlePresent(text1));
    }
}
