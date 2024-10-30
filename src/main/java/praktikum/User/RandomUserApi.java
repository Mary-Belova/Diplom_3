package praktikum.User;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static praktikum.Constant.CREATE_ACCOUNT;
import static praktikum.Constant.OK_200;

public class RandomUserApi {
    private String name;
    private String email;
    private String password;

    public RandomUserApi(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static RandomUserApi from(RandomUser newRandomUser) {
        return new RandomUserApi(RandomUser.getName(), newRandomUser.getEmail(), RandomUser.getPassword());
    }

    @Step("Post запрос на создание нового пользователя")
    public static Response apiCreateNewUser(RandomUserApi newRandomUser) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(newRandomUser)
                .when()
                .post(CREATE_ACCOUNT);
        return response;
    }
    @Step("Проверка успешного создания пользователя")
    public static void checkCreatedUser(Response response) {

        response.then().statusCode(OK_200)
                .and().assertThat().body("success", equalTo(true))
                .and().assertThat().body("user", notNullValue())
                .and().assertThat().body("refreshToken", notNullValue());

    }

}