package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;
import lombok.Data;
import org.assertj.core.api.Assertions;
import ui.steps.BookStoreUiSteps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Data
public class BookCatalogPage {

    private final BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();

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

    @Step("Нажать на кнопку Logout")
    public void clickLogoutButton() {
        logoutButton.click();
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

    @Step("Убедиться, что пользователь успешно авторизовался")
    public void checkUserIsLoggedIn() {
        assertThat(userNameValue.getText()).isEqualTo(TestEnvironmentConfigurator.getBookStoreConfig().login());
    }

    @Step("Отсортировать книги по названию")
    public void sortBookItemsByTitle() {
        titleButton.click();
    }

    @Step("Отсортировать книги по издательству")
    public void sortBookItemsByPublisher() {
        publisherButton.click();
    }

    @Step("Отсортировать книги по автору")
    public void sortBookItemsByAuthor() {
        authorButton.click();
    }

    @Step("Убедиться, что книги отсортированы по названию в алфавитном порядке")
    public void checkBooksAreSortedAscendingByTitle() {
        List<String> titles = titleTexts.texts();
        List<String> sortedTitles = sortAscending(titles);
        Assertions.assertThat(titles).isEqualTo(sortedTitles);
    }

    @Step("Убедиться, что книги отсортированы по издательству в алфавитном порядке")
    public void checkBooksAreSortedAscendingByPublisher() {
        List<String> publishers = getTextFromCells(publisherCells);
        List<String> sortedPublishers = sortAscending(publishers);
        Assertions.assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Step("Убедиться, что книги отсортированы по автору в алфавитном порядке")
    public void checkBooksAreSortedAscendingByAuthor() {
        List<String> authors = getTextFromCells(authorCells);
        List<String> sortedAuthors = sortAscending(authors);
        Assertions.assertThat(authors).isEqualTo(sortedAuthors);
    }

    @Step("Убедиться, что книги отсортированы по названию в обратном алфавитном порядке")
    public void checkBooksAreSortedDescendingByTitle() {
        List<String> titles = titleTexts.texts();
        List<String> sortedTitles = sortDescending(titles);
        Assertions.assertThat(titles).isEqualTo(sortedTitles);
    }

    @Step("Убедиться, что книги отсортированы по издательству в обратном алфавитном порядке")
    public void checkBooksAreSortedDescendingByPublisher() {
        List<String> publishers = getTextFromCells(publisherCells);
        List<String> sortedPublishers = sortDescending(publishers);
        Assertions.assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Step("Убедиться, что книги отсортированы по автору в обратном алфавитном порядке")
    public void checkBooksAreSortedDescendingByAuthor() {
        List<String> authors = getTextFromCells(authorCells);
        List<String> sortedAuthors = sortDescending(authors);
        Assertions.assertThat(authors).isEqualTo(sortedAuthors);
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

    @Step("Отсортировать список полученных строк в алфавитном порядке")
    public List<String> sortAscending(List<String> items) {

        return items.stream().sorted().collect(Collectors.toList());
    }

    @Step("Отсортировать список полученных строк в обратном алфавитном порядке")
    public List<String> sortDescending(List<String> items) {

        return items.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    @Step("Убедиться, что в каталоге найдены книги по критерию")
    public void checkBooksAreFilteredByTitle(String searchQuery) {
        List<String> titles = titleTexts.texts();
        Assertions.assertThat(titles).allMatch(title -> title.contains(searchQuery));
    }

    @Step("Убедиться, что в каталоге найдены книги по критерию")
    public void checkBooksAreFilteredByPublisher(String searchQuery) {
        List<String> publishers = getTextFromCells(publisherCells);
        Assertions.assertThat(publishers).allMatch(publisher -> publisher.contains(searchQuery));
    }

    @Step("Убедиться, что в каталоге найдены книги по критерию")
    public void checkBooksAreFilteredByAuthor(String searchQuery) {
        List<String> authors = getTextFromCells(authorCells);
        Assertions.assertThat(authors).allMatch(author -> author.contains(searchQuery));
    }
}