import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class BlogPage extends AbstractPage {
    public BlogPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNextPage() {
        return nextPage;
    }

    public WebElement getPrevPage() {
        return prevPage;
    }
    public WebElement getNotMyPosts() {
        return notMyPosts;
    }

    public List<WebElement> getGrid() {
        return grid;
    }
    public WebElement getPost() {
        return post;
    }

    public List<WebElement> getNextPageList() {
        return nextPageList;
    }

    public List<WebElement> getPrevPageList() {
        return prevPageList;
    }

    @FindBy(xpath = "//a[.='Next Page']")
    private List<WebElement> nextPageList;

    @FindBy(xpath = "//a[.='Previous Page']")
    private WebElement prevPage;

    @FindBy(xpath = "//a[.='Previous Page']")
    private List<WebElement> prevPageList;

    @FindBy(xpath = "//*[@id=\"SMUI-form-field-1\"]")
    private WebElement notMyPosts;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[3]/div[1]/a")
    private List<WebElement> grid;

    @FindBy(xpath = "//*[@id=\"app\"]/main/div/div[3]/div[1]/a[1]")
    private WebElement post;

    @FindBy(xpath = "//a[.='Next Page']")
    private WebElement nextPage;

    @Step("Click Next")
    public void clickNext(){
        nextPage.click();
    }

    @Step("Click Prev")
    public void clickPrev(){
        prevPage.click();
    }

    @Step("Click NotMyPosts")
    public void clickNotMyPosts(){
        notMyPosts.click();
    }

}

