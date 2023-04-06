import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"login\"]/div[1]/label/input")
    private WebElement usernameInput;

    @FindBy(xpath = "//*[@id=\"login\"]/div[2]/label/input")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"login\"]/div[3]/button")
    private static WebElement buttonLogin;

    @FindBy(xpath = "//span[.='Home']")
    private static WebElement home;

    @FindBy(xpath = "//h1[.='Blog']")
    private static WebElement blog;

    public static WebElement getInvalidCredentials() {
        return invalidCredentials;
    }

    @FindBy(xpath = "//p[.='Invalid credentials.']")
    private static WebElement invalidCredentials;


    public static WebElement getBlog() {
        return blog;
    }

    public static WebElement getHome() {
        return home;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static WebElement getButtonLogin() {
        return buttonLogin;
    }


    @Step("Input username")
    public LoginPage inputUsername(String username){
        usernameInput.click();
        usernameInput.sendKeys(username);
        return this;
    }

    @Step("Input password")
    public LoginPage inputPassword(String password){
        passwordInput.click();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click Login")
    public void clickLogin(){
        buttonLogin.click();
    }



}

