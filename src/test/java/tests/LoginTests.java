package tests;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("authorization")
@Epic("Книжный магазин DemoQA")
@Story("Авторизация пользователя")
@Feature("Успешная и неуспешная авторизация")
public class LoginTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulLogin() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getDisplayedLogin()).isEqualTo(TestEnvironmentConfigurator.getConfig().login());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный логин")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidUserName() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName("invalid_login");
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный пароль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidPassword() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword("invalid_password");
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }
}