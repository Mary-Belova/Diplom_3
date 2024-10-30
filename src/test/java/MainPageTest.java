import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.Pages.MainPage;
import static praktikum.Pages.MainPage.*;

public class MainPageTest {

    @Rule
    public DriverRule driverRule = new DriverRule();


    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\" в конструкторе бургера на главной странице продукта")
    public void switchSauceTabs() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openURL(driver);

        clickSauceTab(driver);
        checkSauceTabIsEnable(driver);
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Начинки\" в конструкторе бургера на главной странице продукта")
    public void switchFillingTabs() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openURL(driver);

        clickFillingTab(driver);
        checkFillingTabIsEnable(driver);
    }

    //Для составления теста на проверку перехода к разделу "Булки" необходимо кликнуть на другой раздел,
    //потом перейти в проверяемый раздел. Т.к. раздел "Булки" активен в стартовом состоянии страницы
    @Test
    @DisplayName("Проверка перехода к разделу \"Булки\" в конструкторе бургера на главной странице продукта")
    public void switchBunTabs() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openURL(driver);

        clickFillingTab(driver);
        checkFillingTabIsEnable(driver);

        clickBunTab(driver);
        checkBunTabIsEnable(driver);
    }


    @Test
    @DisplayName("Проверка перехода на страницу авторизации по ссылке \"Личный кабинет\" в хедере страницы")
    public void loadLoginPageByClickButtonInHeader() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openURL(driver);

        clickPersonalAccountButtonInHeader(driver);
        checkLoginPageIsEnable(driver);
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу по клику логотипа \"Stellar Burgers\" в хедере страницы")
    public void loadHomePageByClickOnLogoInHeader() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openLoginPage(driver);

        clickLogo(driver);
        checkLoadHomePage(driver);
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу по ссылке \"Конструктор\" в хедере страницы")
    public void loadHomePageByClickOnConstructorInHeader() {
        WebDriver driver = driverRule.getDriver();
        MainPage.openLoginPage(driver);

        clickConstructorInHeader(driver);
        checkLoadHomePage(driver);
    }
}