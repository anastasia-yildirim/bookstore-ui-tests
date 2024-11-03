package steps.ui;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;
import pages.BookCatalogPage;
import pages.LoginPage;
import pages.ProfilePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class BookStoreUiSteps {

    ProfilePage profilePage = new ProfilePage();
    LoginPage loginPage = new LoginPage();
    BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Step("Открыть профиль в UI")
    public void openProfilePage() {
        open("/profile");
    }

    @Step("Удалить книгу из профиля")
    public void deleteBookFromProfile(String isbn) {
        $(".ReactTable").$("a[href='/profile?book=" + isbn + "']").shouldBe(visible);
        $("#delete-record-undefined").scrollTo().click();
        $("#closeSmallModal-ok").click();
    }

    @Step("Убедиться, что отображается сообщение для незалогиненного пользователя")
    public void checkNotLoggedInMessageIsDisplayed() {
        profilePage.getNotLoggedInLabel().shouldHave(text(profilePage.getNotLoggedInText()));
    }

    @Step("Открыть страницу авторизации в UI")
    public void openLoginPage() {
        open("/login");
    }

    @Step("Ввести логин")
    public void enterUserName(String login) {
        loginPage.getUserName().sendKeys(login);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        loginPage.getPassword().sendKeys(password);
    }

    @Step("Нажать на кнопку Login")
    public void clickLoginButton() {
        loginPage.getLoginButton().click();
    }

    @Step("Получить отобразившийся логин пользователя")
    public String getDisplayedLogin() {

        return bookCatalogPage.getUserNameValue().getText();
    }

    @Step("Получить отобразившееся валидационное сообщение")
    public String getValidationMessageText() {

        return loginPage.getValidationMessage().getText();
    }

    @Step("Убедиться, что пользователь авторизован")
    public void checkUserIsLoggedIn() {
        assertThat(getDisplayedLogin()).isEqualTo(TestEnvironmentConfigurator.getConfig().login());
    }

    @Step("Открыть страницу книжного каталога")
    public void openBookCatalogPage() {
        open("/books");
    }

    @Step("Нажать на кнопку Logout")
    public void clickLogoutButton() {
        bookCatalogPage.getLogoutButton().click();
    }

    @Step("Получить название отобразившейся страницы")
    public String getDisplayedPageTitle() {

        return loginPage.getPageTitle().getText();
    }
}