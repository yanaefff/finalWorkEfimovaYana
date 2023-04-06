import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PostsWebTests extends AbstractWebTest {


    @Test
    @DisplayName("Мои посты содержит 10 постов")
    void contains10posts() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        Thread.sleep(3000);
        Assertions.assertEquals(blogPage.getGrid().size(), 10);
    }

    @Test
    @DisplayName("Мои посты содержит 0 постов")
    void contains0posts() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethodEmpty();
        Thread.sleep(3000);
        Assertions.assertEquals(blogPage.getGrid().size(), 0);
    }

    @Test
    @DisplayName("Каждый пост содержит заголовок, картинку и описание")
    void containsPost() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        for (WebElement el:blogPage.getGrid()) {
            List<WebElement> insidePost = el.findElements(By.xpath("./child::*"));
            Assertions.assertEquals(insidePost.size(), 3);
            Assertions.assertEquals(insidePost.get(0).getTagName(), "img");
            Assertions.assertEquals(insidePost.get(1).getTagName(), "h2");
            Assertions.assertEquals(insidePost.get(2).getTagName(), "div");
        }
    }

    @Test
    @DisplayName("Отображение чужих постов")
    void login() throws InterruptedException {
        BlogPage blogPage = new BlogPage(getWebDriver());
        loginMethod();
        blogPage.clickNotMyPosts();
        Thread.sleep(5000);
        Assertions.assertEquals(blogPage.getGrid().size(), 4);
        Assertions.assertTrue(blogPage.getNotMyPosts().getAttribute("aria-checked").equals("true"));
    }

}
