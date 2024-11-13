package tests;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.BookCatalogPage;
import ui.pages.LoginPage;

@Epic("Книжный магазин DemoQA")
@Feature("Авторизация пользователя")
@Story("Успешная и неуспешная авторизация")
public class LoginTests extends TestBase {

    private final LoginPage loginPage = new LoginPage();
    private final BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Test
    @DisplayName("Успешная авторизация")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserName(TestEnvironmentConfigurator.getBookStoreConfig().login());
        loginPage.enterPassword(TestEnvironmentConfigurator.getBookStoreConfig().password());
        loginPage.clickLoginButton();
        bookCatalogPage.checkUserIsLoggedIn();
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный логин")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidUserName() {
        loginPage.openLoginPage();
        loginPage.enterUserName("invalid_login");
        loginPage.enterPassword(TestEnvironmentConfigurator.getBookStoreConfig().password());
        loginPage.clickLoginButton();
        loginPage.checkValidationMessage();
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный пароль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidPassword() {
        loginPage.openLoginPage();
        loginPage.enterUserName(TestEnvironmentConfigurator.getBookStoreConfig().login());
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();
        loginPage.checkValidationMessage();
    }
}