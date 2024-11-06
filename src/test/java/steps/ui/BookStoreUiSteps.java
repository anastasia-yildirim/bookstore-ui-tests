package steps.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;
import pages.BookCatalogPage;
import pages.LoginPage;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BookStoreUiSteps {

    private final ProfilePage profilePage = new ProfilePage();
    private final LoginPage loginPage = new LoginPage();
    private final BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Step("Открыть страницу")
    public void openPage(String pagePath) {
        open(pagePath);
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

    @Step("Нажать на кнопку Logout")
    public void clickLogoutButton() {
        bookCatalogPage.getLogoutButton().click();
    }

    @Step("Перейти в другую вкладку")
    public void switchToAnotherTab(int tabIndex) {
        switchTo().window(tabIndex);
    }

    @Step("Перейти в профиль со страницы авторизации (пользователь авторизован)")
    public void goToProfileFromLoginPageWhileLoggedIn() {
        loginPage.getProfileLink().shouldBe(visible).click();
    }

    @Step("Получить название отобразившейся страницы")
    public String getDisplayedPageTitle() {

        return loginPage.getPageTitle().getText();
    }

    @Step("Открыть страницу в новой вкладке")
    public void openPageInAnotherTab(String pagePath, int tabIndex) {
        executeJavaScript("window.open('" + TestEnvironmentConfigurator.getConfig().getBaseUrl() + pagePath
                + "', '_blank');");
        switchTo().window(tabIndex);
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

    @Step("Получить список отображенных названий книг")
    public List<String> getBookTitles() {

        return bookCatalogPage.getTitleTexts().texts();
    }

    @Step("Отсортировать список полученных строк в алфавитном порядке")
    public List<String> sortAscending(List<String> items) {

        return items.stream().sorted().collect(Collectors.toList());
    }

    @Step("Отсортировать список полученных строк в обратном алфавитном порядке")
    public List<String> sortDescending(List<String> items) {

        return items.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    @Step("Ввести значение в строку поиска")
    public void searchBooksBy(String searchQuery) {
        bookCatalogPage.getSearchBox().setValue(searchQuery);
    }

    @Step("Изменить количество отображаемых строк на одной странице каталога")
    public void changeAmountOfRowsOnCatalogPage(String selectedOption) {
        bookCatalogPage.getRowsCountOptionButton().selectOption(selectedOption);
    }

    @Step("Посчитать максимально возможное количество строк")
    public int countMaxRows(String selectedOption) {

        return Integer.parseInt(selectedOption.replaceAll("[^0-9]", ""));
    }

    @Step("Получить количество отображенных строк")
    public int countActualRows() {

        return bookCatalogPage.getRows().size();
    }
}