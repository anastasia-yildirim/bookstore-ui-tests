package tests;

import helpers.WithLogin;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import steps.ui.BookStoreUiSteps;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class LogoutTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешный выход пользователя")
    @Owner("@anastasiayildirim")
    @WithLogin
    public void successfulLoggingOut() {
        bookStoreUiSteps.openBookCatalogPage();
        //bookStoreUiSteps.checkUserIsLoggedIn();
        sleep(5000);
        bookStoreUiSteps.clickLogoutButton();
        assertThat(bookStoreUiSteps.getDisplayedPageTitle()).isEqualTo(loginPage.getExpectedPageTitle());
    }
}