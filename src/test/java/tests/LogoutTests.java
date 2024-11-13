package tests;

import helpers.WithLogin;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BookCatalogPage;
import pages.LoginPage;
import pages.ProfilePage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Книжный магазин DemoQA")
@Feature("Авторизация пользователя")
@Story("Выход из учетной записи и завершение сеанса пользователя")
public class LogoutTests extends TestBase {

    private final BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    private final LoginPage loginPage = new LoginPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Test
    @DisplayName("Успешный выход пользователя")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void successfulLogout() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.clickLogoutButton();
        loginPage.checkUserIsLoggedOut();
    }

    @Test
    @DisplayName("Проверка истечения сессии после выхода пользователя")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void sessionShouldBeExpiredAfterSuccessfulLogout() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.clickLogoutButton();
        profilePage.openProfilePage();
        profilePage.checkNotLoggedInMessage();
    }

    @Test
    @DisplayName("Проверка истечения сессии в одной вкладке после выхода пользователя в другой вкладке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void multipleTabsLogoutTest() {
        loginPage.openLoginPage();
        Allure.step("Убедиться, что пользователь авторизован");
        assertThat(loginPage.getLoggedInMessage().getText()).isEqualTo(loginPage.getExpectedLoggedInMessage());
        bookStoreUiSteps.openPageInAnotherTab(loginPage.getPath(), 1);
        bookCatalogPage.clickLogoutButton();
        bookStoreUiSteps.switchToAnotherTab(0);
        loginPage.goToProfileFromLoginPageWhileLoggedIn();
        profilePage.checkNotLoggedInMessage();
    }
}