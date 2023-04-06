import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AbstractWebTest {

    static WebDriver webDriver;
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String loginUrl;
    private static String loginUrlWeb;
    private static String login;
    private static String loginNoPosts;
    private static String password;
    private static String passNoPosts;
    private static String invalid;

    @BeforeAll
    static void abstractTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        baseUrl= prop.getProperty("base_url");
        loginUrlWeb= prop.getProperty("login_url_web");
        login= prop.getProperty("login");
        password= prop.getProperty("password");
        invalid= prop.getProperty("invalid");
        loginNoPosts= prop.getProperty("loginNoPosts");
        passNoPosts= prop.getProperty("passNoPosts");
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
    public static String getLoginUrlWeb() {
        return loginUrlWeb;
    }
    public static String getInvalid() {
        return invalid;
    }
    public static String getpassword() {
        return password;
    }
    public static String getlogin() { return login; }
    public static String getloginNoPosts() { return loginNoPosts; }
    public static String getpassNoPosts() { return passNoPosts; }

    @BeforeAll
    static void setDriver(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach
    void initLoginPage(){
        Assertions.assertDoesNotThrow( ()-> getWebDriver().navigate().to(getLoginUrlWeb()),
                "Страница не доступна");
        Assertions.assertTrue(true);
    }

    @AfterAll
    public static void exit(){

        if(webDriver !=null) webDriver.quit();
    }

    public WebDriver getWebDriver(){
        return this.webDriver;
    }

    public void loginMethod() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getlogin());
        loginPage.inputPassword(getpassword());
        loginPage.clickLogin();
    }

    public void loginMethodEmpty() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getWebDriver());
        Thread.sleep(5000);
        loginPage.inputUsername(getloginNoPosts());
        loginPage.inputPassword(getpassNoPosts());
        loginPage.clickLogin();
    }
}
