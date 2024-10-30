import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.LogInPage;
import praktikum.User.RandomUser;
import praktikum.User.RandomUserApi;


import static io.restassured.RestAssured.baseURI;
import static praktikum.User.RandomUserApi.checkCreatedUser;


public class LoginPageTest extends LogInPage {
    @Rule
    public DriverRule driverRule = new DriverRule();


    @Before
    public void setUp() {
        baseURI = "https://stellarburgers.nomoreparties.site/";

    }

    //При успешном создании пользователя аккаунт удаляется через API
    @After
    @DisplayName("Удаление учетной записи пользователя")
    public void deleteAccount() {
        if (tokenForDel != null) {
            deleteUser();
        }
    }

    @Test
    @DisplayName("Проверка авторизации пользователя по кнопке \"Войти в аккаунт\" на главной странице продукта")
    public void successLoginFromHomePage() {

        RandomUser randomUser = RandomUser.newRandomUser();
        Response response = RandomUserApi.apiCreateNewUser(RandomUserApi.from(randomUser));
        checkCreatedUser(response);
        String email = randomUser.getEmail();
        String password = RandomUser.getPassword();

        WebDriver driver = driverRule.getDriver();

        logInAccountButtonOnMainPage(driver, email, password);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя по ссылке на \"Личный кабинет\" из хедера страницы")
    public void successLoginFromLinkInHeader() {

        RandomUser randomUser = RandomUser.newRandomUser();
        Response response = RandomUserApi.apiCreateNewUser(RandomUserApi.from(randomUser));
        checkCreatedUser(response);
        String email = randomUser.getEmail();
        String password = RandomUser.getPassword();

        WebDriver driver = driverRule.getDriver();

        logInFromHeaderLink(driver, email, password);
    }

    @Test
    @DisplayName("Проверка авторизации пользователя по ссылке восстановления пароля")
    public void successLoginFromRefreshPasswordPage() {

        RandomUser randomUser = RandomUser.newRandomUser();
        Response response = RandomUserApi.apiCreateNewUser(RandomUserApi.from(randomUser));
        checkCreatedUser(response);
        String email = randomUser.getEmail();
        String password = RandomUser.getPassword();

        WebDriver driver = driverRule.getDriver();

        logInFromRefreshPasswordPage(driver, email, password);
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void successLogOut () {

        RandomUser randomUser = RandomUser.newRandomUser();
        Response response = RandomUserApi.apiCreateNewUser(RandomUserApi.from(randomUser));
        checkCreatedUser(response);
        String email = randomUser.getEmail();
        String password = RandomUser.getPassword();

        WebDriver driver = driverRule.getDriver();

        driver.get("https://stellarburgers.nomoreparties.site/login");

        authByLinkAndGetTokenFromLocalStorage(driver, email, password);
        clickLogoutButtonAndCheckIt(driver);
    }
}