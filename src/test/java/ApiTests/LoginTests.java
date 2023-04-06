package ApiTests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;


public class LoginTests extends AbstractTest {


    public static String getMyToken()  {
        String token = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", getlogin())
                .formParam("password", getpassword())
                .when()
                .post(getLoginUrl())
                .then()
                .extract()
                .jsonPath()
                .get("token")
                .toString();
        return token;

    }

    @Test
    @Story("Валидная регистрация")
    void postValid()  {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", getlogin())
                .formParam("password", getpassword())
                .when()
                .post(getLoginUrl())
                .then()
                .statusCode(200);
    }

    @Test
    @Story("Невалидный пароль")
    void postInvalidPassword() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", getlogin())
                .formParam("password", getInvalid())
                .when()
                .post(getLoginUrl())
                .then()
                .statusCode(401);
    }

    @Test
    @Story("Невалидный логин")
    void postInvalidLogin() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", getInvalid())
                .formParam("password", getpassword())
                .when()
                .post(getLoginUrl())
                .then()
                .statusCode(401);
    }
        //.formParam("X-Auth-Token","5348e25f39d94f1ef8bb0c00d14c8566")
}
