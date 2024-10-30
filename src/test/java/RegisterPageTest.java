import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import praktikum.Pages.LogInPage;
import praktikum.User.RandomUser;
import praktikum.Pages.RegisterPage;
import static praktikum.Pages.RegisterPage.*;

public class RegisterPageTest extends LogInPage {

    @Rule
    public DriverRule driverRule = new DriverRule();


    //При успешном создании пользователя аккаунт удаляется через API
    @After
    @DisplayName("Удаление учетной записи пользователя")
    public void deleteAccount() {
        if (tokenForDel != null) {
            deleteUser();
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя, пароль: 6 символов")
    public void successRegistrationPasswordGetSixCharacters() {
        WebDriver driver = driverRule.getDriver();
        RegisterPage.openPage(driver);

        //Создание рандомного пользователя с валидным паролем равным 6 символам.
        //В переменные email и password записаны данные для авторизации.
        RandomUser randomUserData = RandomUser.newRandomUser();
        String email = randomUserData.getEmail();
        String password = RandomUser.getPassword();

        //Заполнение формы авторизации
        completionUserData(driver, randomUserData);
        clickButtonRegistration(driver);
        checkSuccessfulRegistration(driver);

        //Авторизация пользователя, получение токена
        authByLinkAndGetTokenFromLocalStorage(driver, email, password);
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя, пароль: 7 символов")
    public void successRegistrationPasswordGetSevenCharacters() {
        WebDriver driver = driverRule.getDriver();
        RegisterPage.openPage(driver);

        RandomUser randomUserData = RandomUser.newRandomUserPasswordSevenCharacters();
        String email = randomUserData.getEmail();
        String password = RandomUser.getPassword();

        completionUserData(driver, randomUserData);
        clickButtonRegistration(driver);
        checkSuccessfulRegistration(driver);
        System.out.println(password);

        authByLinkAndGetTokenFromLocalStorage(driver, email, password);
    }

    @Test
    @DisplayName("Проверка получения ошибки \"Некорректный пароль\" при попытке регистрации аккаунта с 5 символами в поле Password")
    public void errorRegistrationPasswordGetFiveCharacters() {
        WebDriver driver = driverRule.getDriver();
        RegisterPage.openPage(driver);

        RandomUser randomUserData = RandomUser.newRandomUserInvalidPasswordFiveCharacters();

        completionUserData(driver, randomUserData);
        checkErrorRegistrationInvalidPassword(driver);
        getUserToken((WebStorage) driver);

    }
}