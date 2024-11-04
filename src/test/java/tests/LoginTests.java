package tests;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Книжный магазин DemoQA")
@Feature("Авторизация пользователя")
@Story("Успешная и неуспешная авторизация")
public class LoginTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulLogin() {
        bookStoreUiSteps.openPage(loginPage.getPath());
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        Allure.step("Убедиться, что пользователь успешно авторизовался");
        assertThat(bookStoreUiSteps.getDisplayedLogin()).isEqualTo(TestEnvironmentConfigurator.getConfig().login());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный логин")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidUserName() {
        bookStoreUiSteps.openPage(loginPage.getPath());
        bookStoreUiSteps.enterUserName("invalid_login");
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        Allure.step("Убедиться, что отображается сообщение " + loginPage.getExpectedValidationMessage());
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный пароль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginWithInvalidPassword() {
        bookStoreUiSteps.openPage(loginPage.getPath());
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword("invalid_password");
        bookStoreUiSteps.clickLoginButton();
        Allure.step("Убедиться, что отображается сообщение " + loginPage.getExpectedValidationMessage());
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }
}