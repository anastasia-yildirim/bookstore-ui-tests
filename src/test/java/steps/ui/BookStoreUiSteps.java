package steps.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;
import pages.BookCatalogPage;
import pages.LoginPage;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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

    @Step("Получить отображаемое сообщение для пользователя")
    public String getNotLoggedInMessageDisplayed() {

        return profilePage.getNotLoggedInLabel().getText();
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

    @Step("Открыть страницу в новой вкладке")
    public void openPageInAnotherTab(String pagePath) {
        executeJavaScript("window.open('" + TestEnvironmentConfigurator.getConfig().getBaseUrl() + pagePath
                + "', '_blank');");
        switchTo().window(1);
    }

    @Step("Отсортировать книги по нужному столбцу")
    public void sortBookItemsBy(SelenideElement elementToSortBy) {
        elementToSortBy.click();
    }

    @Step("Получить список всех значений в нужном столбце")
    public List<String> getTextFromCells(ElementsCollection cells) {
        List<String> textList = new ArrayList<>();
        for (SelenideElement cell : cells) {
            String cellText = cell.getText().trim();
            if (!cellText.isEmpty()) {
                textList.add(cellText);
            }
        }

        return textList;
    }
}