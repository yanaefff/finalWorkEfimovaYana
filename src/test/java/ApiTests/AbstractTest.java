package ApiTests;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String loginUrl;
    private static String login;
    private static String password;
    private static String invalid;

    @BeforeAll
    static void abstractTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        baseUrl= prop.getProperty("base_url");
        loginUrl= prop.getProperty("login_url");
        login= prop.getProperty("login");
        password= prop.getProperty("password");
        invalid= prop.getProperty("invalid");
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
    public static String getLoginUrl() {
        return loginUrl;
    }
    public static String getInvalid() {
        return invalid;
    }
    public static String getpassword() {
        return password;
    }
    public static String getlogin() {
        return login;
    }
}
