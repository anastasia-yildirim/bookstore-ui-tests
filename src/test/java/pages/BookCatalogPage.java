package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Data
public class BookCatalogPage {

    private String path = "/books";

    private SelenideElement searchBox = $("#searchBox");
    private SelenideElement userNameValue = $("#userName-value");
    private SelenideElement logoutButton = $("#submit");
    private SelenideElement titleButton = $$(".rt-resizable-header-content").find(Condition.text("Title"));
    private SelenideElement publisherButton = $$(".rt-resizable-header-content").find(Condition.text("Publisher"));
    private SelenideElement authorButton = $$(".rt-resizable-header-content").find(Condition.text("Author"));
    private ElementsCollection publisherCells = $$(".rt-tbody .rt-tr .rt-td:nth-of-type(4)");
    private ElementsCollection authorCells = $$(".rt-tbody .rt-tr .rt-td:nth-of-type(3)");
    private ElementsCollection titleTexts = $$(".action-buttons .mr-2 a");
    private SelenideElement row = $(".rt-tr-group");
    private ElementsCollection rows = $$(".rt-tr-group");
    private SelenideElement rowsCountOptionButton = $("select[aria-label='rows per page']");

    @Step("Открыть страницу каталога")
    public void openCatalogPage() {
        open(path);
    }

    @Step("Получить отобразившийся логин пользователя")
    public String getDisplayedLogin() {

        return userNameValue.getText();
    }

    @Step("Нажать на кнопку Logout")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Получить список отображенных названий книг")
    public List<String> getBookTitles() {

        return titleTexts.texts();
    }

    @Step("Ввести значение в строку поиска")
    public void searchBooksBy(String searchQuery) {
        searchBox.setValue(searchQuery);
    }

    @Step("Изменить количество отображаемых строк на одной странице каталога")
    public void changeAmountOfRowsOnCatalogPage(String selectedOption) {
        rowsCountOptionButton.selectOption(selectedOption);
    }

    @Step("Получить количество отображенных строк")
    public int countActualRows() {

        return rows.size();
    }
}