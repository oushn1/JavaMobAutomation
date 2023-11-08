import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
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
    protected int timeout = 5;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Before
    public void beforeTests()
    {
        WebDriverWait wait = new WebDriverWait(this.driver, this.timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("org.wikipedia:id/fragment_onboarding_skip_button"))).click();
    }

    @Test
    public void testSearch() throws InterruptedException {
        String word_to_search = "docker";
        By locator_search = By.id("org.wikipedia:id/search_container");
        By locator_clear_search = By.id("org.wikipedia:id/search_close_btn");
        By locator_search_result_items = By.id("org.wikipedia:id/page_list_item_title");

        WebElement search = MainPageObject.findElement(locator_search, "No search field found");
        search.click();
        search.sendKeys(word_to_search);
        List<WebElement> search_results = MainPageObject.findElements(locator_search_result_items, "No search results found");

        Assert.assertFalse(search_results.isEmpty());
        List<WebElement> search_results_cleared = new ArrayList<>();
        MainPageObject.findElement(locator_clear_search, "").click();
        try {
            search_results_cleared = MainPageObject.findElements(locator_search_result_items, "");
        } catch (Exception exception) {};

        Assert.assertTrue(search_results_cleared.isEmpty());
    }

    @Test
    public void testSaveArticleToMyList() throws InterruptedException {
        String text1 = "Plutonium";
        String text2 = "Uranium";
        String list_title = "List1";

        //Find and save article 1
        MainPageObject.findAndOpenArticle(text1);
        MainPageObject.saveArticleToReadingList();

        //Find and save article 2
        MainPageObject.goHome();
        MainPageObject.findAndOpenArticle(text2);
        MainPageObject.saveArticleToReadingList();

        //Remove article 1
        MainPageObject.goHome();
        MainPageObject.goSaved();
        MainPageObject.removeArticleFromReadingList(text1, list_title);

        //Check if article 2 remains = Open article 2
        MainPageObject.openSavedArticle(text2);

        //Check if the article has a correct title
        WebElement article_title = MainPageObject.findElement(new MobileBy.ByAccessibilityId(text2), "");

        //Remove article 2
        MainPageObject.goHome();
        MainPageObject.goSaved();
        MainPageObject.removeArticleFromReadingList(text2, list_title);

    }

    @Test
    public void testSearchNoWait() throws InterruptedException {
        String text1 = "Plutonium";
        MainPageObject.findAndOpenArticle(text1);
        Assert.assertTrue(MainPageObject.isTitlePresent(text1));
    }
}