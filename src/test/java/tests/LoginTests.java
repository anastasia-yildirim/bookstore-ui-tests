package tests;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.Owner;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BookCatalogPage;
import pages.LoginPage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Data
public class LoginTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    BookCatalogPage bookCatalogPage = new BookCatalogPage();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация")
    @Owner("@anastasiayildirim")
    public void successfulLoggingIn() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getDisplayedLogin()).isEqualTo(TestEnvironmentConfigurator.getConfig().login());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный логин")
    @Owner("@anastasiayildirim")
    public void unsuccessfulLoggingInWithInvalidUserName() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName("invalid_login");
        bookStoreUiSteps.enterPassword(TestEnvironmentConfigurator.getConfig().password());
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }

    @Test
    @DisplayName("Неуспешная авторизация - неверный пароль")
    @Owner("@anastasiayildirim")
    public void unsuccessfulLoggingInWithInvalidPassword() {
        bookStoreUiSteps.openLoginPage();
        bookStoreUiSteps.enterUserName(TestEnvironmentConfigurator.getConfig().login());
        bookStoreUiSteps.enterPassword("invalid_password");
        bookStoreUiSteps.clickLoginButton();
        assertThat(bookStoreUiSteps.getValidationMessageText()).isEqualTo(loginPage.getExpectedValidationMessage());
    }
}