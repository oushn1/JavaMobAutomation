package tests;

import io.appium.java_client.MobileBy;
import lib.CoreTestCase;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticleTestsWeb extends CoreTestCase {

    protected void setUp() throws Exception
    {
        super.setUp();
        this.beforeTests();
    }

    public void beforeTests()
    {
    }

    public void testSaveArticleToMyList() throws InterruptedException {
        String text1 = "Plutonium";
        String text2 = "Uranium";
        String list_title = "List1";

        //Find and save article 1
        SearchPageObjectWeb.findAndOpenArticle(text1);
        ArticlePageObjectWeb.saveArticleToReadingList();

        //Find and save article 2
        MainPageObject.goHome();
        SearchPageObjectWeb.findAndOpenArticle(text2);
        ArticlePageObjectWeb.saveArticleToReadingList();

        //Remove article 1
        MainPageObject.goHome();
        MainPageObject.goSaved();
        ArticlePageObjectWeb.removeArticleFromReadingList(text1, list_title);

        //Check if article 2 remains = Open article 2
        ArticlePageObjectWeb.openSavedArticle(text2);

        //Check if the article has a correct title
        WebElement article_title = MainPageObject.findElement(new MobileBy.ByAccessibilityId(text2), "");

        //Remove article 2
        MainPageObject.goHome();
        MainPageObject.goSaved();
        ArticlePageObjectWeb.removeArticleFromReadingList(text2, list_title);
    }
}
