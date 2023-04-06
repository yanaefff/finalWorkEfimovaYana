import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PageWebTests extends AbstractWebTest {


    @Test
    @DisplayName("Переход на страницу 2")
    void nextPage() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        blogPage.clickNext();
        Thread.sleep(3000);

        Assertions.assertTrue(webDriver.getCurrentUrl().equals("https://test-stand.gb.ru/?page=2"));
        Assertions.assertFalse(blogPage.getPrevPage().getAttribute("class").contains("disabled"));
        Assertions.assertFalse(blogPage.getNextPage().getAttribute("class").contains("disabled"));

    }

    @Test
    @DisplayName("Переход на предыдущую страницу с 1й")
    void prevPage1() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        blogPage.clickPrev();
        Thread.sleep(3000);
        Assertions.assertTrue(webDriver.getCurrentUrl().equals("https://test-stand.gb.ru/"));
        Assertions.assertTrue(blogPage.getPrevPage().getAttribute("class").contains("disabled"));
        Assertions.assertFalse(blogPage.getNextPage().getAttribute("class").contains("disabled"));
    }

    @Test
    @DisplayName("Переход на предыдущую страницу с 3й")
    void prevPage2() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        blogPage.clickNext();
        Thread.sleep(3000);
        blogPage.clickNext();
        Thread.sleep(3000);
        blogPage.clickPrev();
        Thread.sleep(3000);
        Assertions.assertTrue(webDriver.getCurrentUrl().equals("https://test-stand.gb.ru/?page=2"));
        Assertions.assertFalse(blogPage.getPrevPage().getAttribute("class").contains("disabled"));
        Assertions.assertFalse(blogPage.getNextPage().getAttribute("class").contains("disabled"));
    }

    @Test
    @DisplayName("Переход на последнюю страницу")
    void lastPage() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        blogPage.clickNext();
        Thread.sleep(3000);
        blogPage.clickNext();
        Thread.sleep(3000);
        Assertions.assertTrue(webDriver.getCurrentUrl().equals("https://test-stand.gb.ru/?page=3"));
        Assertions.assertTrue(blogPage.getNextPage().getAttribute("class").contains("disabled"));
        Assertions.assertFalse(blogPage.getPrevPage().getAttribute("class").contains("disabled"));
    }


    @Test
    @DisplayName("Нет страниц, если нет постов")
    void contsains0posts() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethodEmpty();
        Thread.sleep(3000);
        Assertions.assertEquals(blogPage.getNextPageList().size(), 0);
        Assertions.assertEquals(blogPage.getPrevPageList().size(), 0);
    }

}
