package ApiTests;

import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class PostsTests extends AbstractTest {


    @Test
    @Story("OwnerNotMe ASC page 1")
    void OwnerNotMeASCpage1()  {
        JsonPath response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 4);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "1");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "2");
        Assertions.assertTrue((Integer.parseInt(response.get("data[0].id").toString())) < (Integer.parseInt(response.get("data[1].id").toString())));

    }

    @Test
    @Story("OwnerNotMe ASC page 10")
    void OwnerNotMeASCpage10()  {
        JsonPath response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "10")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 4);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "9");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "11");
        Assertions.assertTrue((Integer.parseInt(response.get("data[0].id").toString())) < (Integer.parseInt(response.get("data[1].id").toString())));

    }

    @Test
    @Story("OwnerNotMe ALL")
    void OwnerNotMeALL()  {
        JsonPath response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ALL")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 4);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "1");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "2");

    }

    @Test
    @Story("OwnerNotMe DESC")
    void OwnerNotMeDESC()  {
        JsonPath response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 4);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "1");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "2");
        Assertions.assertTrue((Integer.parseInt(response.get("data[0].id").toString())) > (Integer.parseInt(response.get("data[1].id").toString())));

    }

    @Test
    @Story("OwnerNotMe contains data")
    void OwnerNotMeContainsData()  {
        JsonPath response = given()
                .queryParam("owner", "notMe")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertNotNull(response.get("data[0].title").toString());
        Assertions.assertNotNull(response.get("data[0].description").toString());
        Assertions.assertNotNull(response.get("data[0].content").toString());
        Assertions.assertNotNull(response.get("data[0].authorId").toString());
        Assertions.assertNotNull(response.get("data[0].mainImage").toString());
        Assertions.assertNotNull(response.get("data[0].updatedAt"));
        Assertions.assertNotNull(response.get("data[0].createdAt"));
        Assertions.assertNotNull(response.get("data[0].labels").toString());
        Assertions.assertNotNull(response.get("data[0].draft").toString());

    }

    @Test
    @Story("MyPosts ASC page 1")
    void MyPostsASCpage1()  {
        JsonPath response = given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 10);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "1");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "2");
        Assertions.assertEquals(response.get("meta.count").toString(), "11");
        Assertions.assertTrue((Integer.parseInt(response.get("data[0].id").toString())) < (Integer.parseInt(response.get("data[1].id").toString())));

    }

    @Test
    @Story("MyPosts ASC page 10 empty")
    void MyPostsASCpage10empty()  {
        JsonPath response = given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "10")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 0);

    }

    @Test
    @Story("MyPosts DESC")
    void MyPostsDESC()  {
        JsonPath response = given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertEquals(response.getList("data").size(), 4);
        Assertions.assertEquals(response.get("meta.prevPage").toString(), "1");
        Assertions.assertEquals(response.get("meta.nextPage").toString(), "2");
        Assertions.assertTrue((Integer.parseInt(response.get("data[0].id").toString())) > (Integer.parseInt(response.get("data[1].id").toString())));


    }

    @Test
    @Story("MyPosts contains data")
    void MyPostscontainsdata()  {
        JsonPath response = given()
                .queryParam("page", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath();

        Assertions.assertNotNull(response.get("data[0].title").toString());
        Assertions.assertNotNull(response.get("data[0].description").toString());
        Assertions.assertNotNull(response.get("data[0].content").toString());
        Assertions.assertNotNull(response.get("data[0].authorId").toString());
        Assertions.assertNotNull(response.get("data[0].mainImage").toString());
        Assertions.assertNotNull(response.get("data[0].updatedAt"));
        Assertions.assertNotNull(response.get("data[0].createdAt"));
        Assertions.assertNotNull(response.get("data[0].labels").toString());
        Assertions.assertNotNull(response.get("data[0].draft").toString());

    }

    @Test
    @Story("MyPosts wrong page value")
    void MyPostswrongpagevalue()  {
        JsonPath response = given()
                .queryParam("page", "PAGEONE")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body()
                .jsonPath();

    }

    @Test
    @Story("MyPosts wrong order value")
    void MyPostswrongordervalue()  {
        JsonPath response = given()
                .queryParam("page", "1")
                .queryParam("order", "1")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body()
                .jsonPath();

    }

    @Test
    @Story("MyPosts wrong sort value")
    void MyPostswrongsortvalue()  {
        JsonPath response = given()
                .queryParam("page", "1")
                .queryParam("sort", "asc")
                .when()
                .header("X-Auth-Token", LoginTests.getMyToken())
                .get(getBaseUrl())
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body()
                .jsonPath();

    }
}
