import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;
    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;

    protected int timeout = 5;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
    }

    @Before
    public void beforeTests()
    {
        WebElement skip_button = this.MainPageObject.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "");
        skip_button.click();
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
    public void testSaveArticleToMyList() throws InterruptedException {
        String text1 = "Plutonium";
        String text2 = "Uranium";
        String list_title = "List1";

        //Find and save article 1
        SearchPageObject.findAndOpenArticle(text1);
        ArticlePageObject.saveArticleToReadingList();

        //Find and save article 2
        MainPageObject.goHome();
        SearchPageObject.findAndOpenArticle(text2);
        ArticlePageObject.saveArticleToReadingList();

        //Remove article 1
        MainPageObject.goHome();
        MainPageObject.goSaved();
        ArticlePageObject.removeArticleFromReadingList(text1, list_title);

        //Check if article 2 remains = Open article 2
        ArticlePageObject.openSavedArticle(text2);

        //Check if the article has a correct title
        WebElement article_title = MainPageObject.findElement(new MobileBy.ByAccessibilityId(text2), "");

        //Remove article 2
        MainPageObject.goHome();
        MainPageObject.goSaved();
        ArticlePageObject.removeArticleFromReadingList(text2, list_title);
    }

    @Test
    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        SearchPageObject.findAndOpenArticle(text1);
        Assert.assertTrue(ArticlePageObject.isTitlePresent(text1));
    }
}