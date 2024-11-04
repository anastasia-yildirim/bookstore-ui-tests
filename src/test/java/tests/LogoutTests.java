package tests;

import helpers.WithLogin;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import steps.ui.BookStoreUiSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("authorization")
@Epic("Книжный магазин DemoQA")
@Story("Авторизация пользователя")
@Feature("Выход из учетной записи и завершение сеанса пользователя")
public class LogoutTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();
    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    @DisplayName("Успешный выход пользователя")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void successfulLogout() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.clickLogoutButton();
        softAssertions.assertThat(bookStoreUiSteps.getDisplayedPageTitle()).isEqualTo(loginPage.getExpectedPageTitle());
        softAssertions.assertThat(loginPage.getLoggedInUserNameValue().isDisplayed()).isFalse();
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Проверка истечения сессии после выхода пользователя")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void sessionShouldBeExpiredAfterSuccessfulLogout() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.clickLogoutButton();
        bookStoreUiSteps.openProfilePage();
        assertThat(bookStoreUiSteps.getNotLoggedInMessageDisplayed()).isEqualTo(profilePage.getNotLoggedInText());
    }

    @Test
    @DisplayName("Проверка истечения сессии в одной вкладке после выхода пользователя в другой вкладке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    @WithLogin
    public void multipleTabsLogoutTest() {
        bookStoreUiSteps.openLoginPage();
        assertThat(loginPage.getLoggedInMessage().getText()).isEqualTo(loginPage.getExpectedLoggedInMessage());

        bookStoreUiSteps.openPageInAnotherTab(loginPage.getPath());
        bookStoreUiSteps.clickLogoutButton();

        switchTo().window(0);
        loginPage.getProfileLink().shouldBe(visible).click();
        assertThat(bookStoreUiSteps.getNotLoggedInMessageDisplayed()).isEqualTo(profilePage.getNotLoggedInText());
    }
}