package praktikum.Pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static io.restassured.RestAssured.given;
import static praktikum.Constant.*;

public class LogInPage {

    //Кнопка "Войти" на странице авторизации
    By logInButton  = By.className("button_button__33qZ0");

    //Поля ввода почты и пароля пользователя
    By fieldEmail = By.xpath(".//input[@type='text']");
    By fieldPassword = By.xpath(".//input[@type='password']");

    //Кнопка выхода из аккаунта, расположена в личном кабинете пользователя
    By logOutButton = By.className("Account_button__14Yp3");

    //Кнопки восстановления пароля и входа со страницы восстановления последнего
    By refreshPasswordLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    By loginFromRefreshPasswordPage = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    //Ссылка на личный кабинет в хедере страницы
    static By personalAccountButtonInHeader = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");

    public static String tokenForDel;

    @Step("Авторизация и получение токена пользователя")
    public void authByLinkAndGetTokenFromLocalStorage(WebDriver driver, String email, String password) {

        driver.get("https://stellarburgers.nomoreparties.site/login");

        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logInButton));


        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(logInButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        getUserToken((WebStorage) driver);

    }

    @Step("Удаление учетной записи пользователя")
    public void deleteUser() {
        given().log().all()
                .header("Content-type", "application/json")
                .auth().oauth2(tokenForDel)
                .baseUri(BASE_URL)
                .when()
                .delete(DELETE_ACCOUNT)
                .then().log().all().assertThat()
                .statusCode(ACCEPT_202);
        System.out.println("Учетная запись клиента успешно удалена");
    }


    @Step("Успешная авторизация по кнопке Войти и получение токена пользователя")
    public void logInAccountButtonOnMainPage (WebDriver driver, String email, String password) {

        driver.get(BASE_URL);

        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logInButton));
        driver.findElement(logInButton).click();
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(logInButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        getUserToken((WebStorage) driver);

    }

    @Step("Проверка успешной авторизация по ссылке в хедере страницы и получение токена пользователя")
    public void logInFromHeaderLink (WebDriver driver, String email, String password) {

        driver.get(BASE_URL);

        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logInButton));
        driver.findElement(personalAccountButtonInHeader).click();
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(logInButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        getUserToken((WebStorage) driver);
    }

    @Step("Проверка успешной регистрации по ссылке \"Личный кабинет\" в хедере страницы и получение токена пользователя")
    public void logInFromRefreshPasswordPage (WebDriver driver, String email, String password) {

        driver.get(BASE_URL);

        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logInButton));
        driver.findElement(personalAccountButtonInHeader).click();
        driver.findElement(refreshPasswordLink).click();
        driver.findElement(loginFromRefreshPasswordPage).click();

        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(logInButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));

        getUserToken((WebStorage) driver);

    }

    @Step("Проверка выхода из аккаунта")
    public void clickLogoutButtonAndCheckIt(WebDriver driver) {
        driver.findElement(personalAccountButtonInHeader).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        driver.findElement(logOutButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIME_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(logInButton));
    }

    public static void getUserToken(WebStorage driver) {
        LocalStorage localStorage = driver.getLocalStorage();
        String accessToken = localStorage.getItem("accessToken");
        tokenForDel = StringUtils.substringAfter(accessToken, " ");
    }

}