package praktikum.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constant;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {

    //Ссылка на личный кабинет в хедере страницы
    static By personalAccountButtonInHeader = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");

    //Локатор страницы авторизации(необходим для проверки перехода на страницу по нажатию на кнопки "Личный кабинет" и "Войти в аккаунт")
    static By loginPage = By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    //Конструктор и лого в хедере страницы
    static By constructor = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p");
    static By logoInHeader = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a"); ////*[@id="root"]/div/header/nav/div/a

    //Разделы: булки, соусы, начинки, на главной странице
    static By bunTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    static By sauceTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    static By fillingTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");



    @Step("Загрузка главной страницы")
    public static void openURL(WebDriver driver) {
        driver.get(Constant.BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
    }

    @Step("Загрузка страницы авторизации")
    public static void openLoginPage (WebDriver driver) {
        driver.get(Constant.LOGIN_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage));
    }



    @Step("Переход к разделу \"Соусы\"")
    public static void clickSauceTab(WebDriver driver) {
        driver.findElement(sauceTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.attributeContains(sauceTab, "class", "current"));
    }

    @Step("Проверка активности раздела \"Соусы\"")
    public static void checkSauceTabIsEnable(WebDriver driver) {
        WebElement elementSauceTab = driver.findElement(sauceTab);
        String sauceTabClass = elementSauceTab.getAttribute("class");
        assertTrue(sauceTabClass.contains("current"));
    }


    @Step("Переход к разделу \"Начинки\"")
    public static void clickFillingTab(WebDriver driver) {
        driver.findElement(fillingTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.attributeContains(fillingTab, "class", "current"));
    }

    @Step("Проверка активности раздела \"Начинки\"")
    public static void checkFillingTabIsEnable(WebDriver driver) {
        WebElement elementFillingTab = driver.findElement(fillingTab);
        String fillingTabClass = elementFillingTab.getAttribute("class");
        assertTrue(fillingTabClass.contains("current"));
    }

    @Step("Переход к разделу \"Булки\"")
    public static void clickBunTab(WebDriver driver) {
        driver.findElement(bunTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.attributeContains(bunTab, "class", "current"));
    }

    @Step("Проверка активности раздела \"Булки\"")
    public static void checkBunTabIsEnable(WebDriver driver) {
        WebElement elementFillingTab = driver.findElement(bunTab);
        String bunTabClass = elementFillingTab.getAttribute("class");
        assertTrue(bunTabClass.contains("current"));
    }

    @Step("Переход на страницу авторизации по клику ссылки \"Личный кабинет\" в хедере страницы")
    public static void clickPersonalAccountButtonInHeader(WebDriver driver) {
        driver.findElement(personalAccountButtonInHeader).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage));
    }

    @Step("Проверка загрузки страницы авторизации")
    public static void checkLoginPageIsEnable (WebDriver driver) {
        assertTrue(driver.findElement(loginPage).isDisplayed());
    }

    @Step("Клик по логотипу \"Stellar Burger\" в хедере страницы")
    public static void clickLogo (WebDriver driver) {
        driver.findElement(logoInHeader).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bunTab));
    }

    @Step("Клик по ссылке \"Конструктор\" в хедере страницы")
    public static void clickConstructorInHeader (WebDriver driver) {
        driver.findElement(constructor).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constant.TIME_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bunTab));
    }

    @Step("Проверка загрузки главной страницы")
    public static void checkLoadHomePage (WebDriver driver) {
        assertTrue(driver.findElement(bunTab).isDisplayed());
    }

}