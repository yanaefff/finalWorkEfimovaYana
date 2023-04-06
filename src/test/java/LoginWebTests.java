import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginWebTests extends AbstractWebTest {

    @Test
    @DisplayName("Валидные логин и пароль")
    void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        BlogPage blogPage = new BlogPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getlogin());
        loginPage.inputPassword(getpassword());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getBlog()));
        Thread.sleep(5000);

        Assertions.assertTrue(LoginPage.getHome().isDisplayed());
        Assertions.assertTrue(blogPage.getNotMyPosts().getAttribute("aria-checked").equals("false"));
    }

    @Test
    @DisplayName("Логин 3 символа")
    void invalidLogin3symb() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getloginNoPosts());
        loginPage.inputPassword(getpassNoPosts());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getBlog()));
        Thread.sleep(5000);

        Assertions.assertTrue(LoginPage.getHome().isDisplayed());
    }


    @Test
    @DisplayName("Невалидный логин")
    void invalidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getInvalid());
        loginPage.inputPassword(getpassword());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getInvalidCredentials()));
        Thread.sleep(3000);

        Assertions.assertTrue(LoginPage.getInvalidCredentials().isDisplayed());
    }

    @Test
    @DisplayName("Невалидный пароль")
    void invalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getlogin());
        loginPage.inputPassword(getInvalid());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getInvalidCredentials()));
        Thread.sleep(3000);

        Assertions.assertTrue(LoginPage.getInvalidCredentials().isDisplayed());
    }

    @Test
    @DisplayName("Логин 2 символа")
    void invalidLogin2symb() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername("11");
        loginPage.inputPassword(getpassword());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getInvalidCredentials()));
        Thread.sleep(3000);

        Assertions.assertTrue(LoginPage.getInvalidCredentials().isDisplayed());
    }

    @Test
    @DisplayName("Логин 21 символ")
    void invalidLogin21symb() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername("123456789123456789123");
        loginPage.inputPassword(getpassword());
        loginPage.clickLogin();

        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.visibilityOf(LoginPage.getInvalidCredentials()));
        Thread.sleep(3000);

        Assertions.assertTrue(LoginPage.getInvalidCredentials().isDisplayed());
    }

    @Test
    @DisplayName("Кнопка LOGIN синего цвета")
    void blueButtonLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        Assertions.assertTrue(LoginPage.getButtonLogin().getCssValue("color").equals("rgba(0, 87, 255, 1)"));

    }



}
