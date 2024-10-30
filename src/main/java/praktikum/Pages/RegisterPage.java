package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constant;
import praktikum.User.RandomUser;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static praktikum.Constant.TIME_WAIT;


public class RegisterPage {

    //Ссылка на форму регистрации нового аккаунта, расположена на странице авторизации
    static By registerANewAccountButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");

    //Поля регистрации аккаунта
    static By fieldRegisterName = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    static By fieldRegisterEmail = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    static By fieldRegisterPassword = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");

    //Кнопка "Зарегистрироваться"
    static By buttonRegisterAnAccount = By.className("button_button__33qZ0");

    //Ошибка "Некорректный пароль", требования к паролю 6 и более символов, проверки по нижней границе: 5 (ошибка), 6, 7
    static By errorInvalidPassword = By.className("input__error");
    static By loginPage = By.xpath("//*[@id=\"root\"]/div/main/div/h2");
    static By reg = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]");

    static By loginButton = By.cssSelector("form button");




    @Step("Загрузка страницы регистрации")
    public static void openPage(WebDriver driver) {
        driver.get(Constant.REGISTRATION_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(reg));
    }



    @Step("Заполнение полей на странице регистрации нового пользователя")
    public static void completionUserData(WebDriver driver, RandomUser randomUserData) {

        driver.findElement(fieldRegisterName).sendKeys(RandomUser.getName());
        driver.findElement(fieldRegisterEmail).sendKeys(randomUserData.getEmail());
        driver.findElement(fieldRegisterPassword).sendKeys(RandomUser.getPassword());
    }

    @Step("Клик по кнопке \"Зарегистрироватья\"")
    public static void clickButtonRegistration(WebDriver driver) {
        driver.findElement(buttonRegisterAnAccount).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage));

    }


    @Step("Проверка успешной регистрации пользователя")
    public static void checkSuccessfulRegistration(WebDriver driver) {

        driver.findElement(loginButton).isDisplayed();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        assertTrue(driver.findElement(loginButton).isDisplayed());

    }

    @Step("Проверка появления ошибки \"Некорректный пароль\" при попытке регистрации аккаунта с паролем менее 6 символов")
    public static void checkErrorRegistrationInvalidPassword(WebDriver driver) {

        driver.findElement(buttonRegisterAnAccount).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(errorInvalidPassword));
        assertTrue(driver.findElement(errorInvalidPassword).isDisplayed());

    }
}