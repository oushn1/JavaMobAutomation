package tests;

import io.appium.java_client.MobileBy;
import lib.CoreTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticleTests extends CoreTestCase {

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
}
