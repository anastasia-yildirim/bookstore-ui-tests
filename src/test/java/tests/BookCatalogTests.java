package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BookCatalogPage;
import steps.ui.BookStoreUiSteps;
import testdata.enums.Publisher;
import testdata.enums.RowsCountOption;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Книжный магазин DemoQA")
@Feature("Действия пользователя в каталоге книг")
@Story("Поиск, фильтрация и сортировка книг в каталоге")
public class BookCatalogTests extends TestBase {

    private final BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    private final BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getBookTitles();
        List<String> sortedTitles = bookStoreUiSteps.sortAscending(titles);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = bookStoreUiSteps.sortAscending(publishers);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = bookStoreUiSteps.sortAscending(authors);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в обратном алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getBookTitles();
        List<String> sortedTitles = bookStoreUiSteps.sortDescending(titles);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = bookStoreUiSteps.sortDescending(publishers);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = bookStoreUiSteps.sortDescending(authors);
        Allure.step("Убедиться, что в каталоге книги отсортированы в правильном порядке");
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по названию")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"Git", "JavaScript", "Design", "Script", "ing"})
    public void filterBookItemsByTitleTest(String searchQuery) {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.searchBooksBy(searchQuery);
        List<String> titles = bookCatalogPage.getBookTitles();
        Allure.step("Убедиться, что в каталоге найдены книги по критерию");
        assertThat(titles).allMatch(title -> title.contains(searchQuery));
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по автору")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @CsvSource({
            "'Richard E. Silverman'",
            "'Addy Osmani'",
            "'Glenn Block et al.'",
            "'Axel Rauschmayer'",
            "'Kyle Simpson'",
            "'Eric Elliott'",
            "'Marijn Haverbeke'",
            "'Nicholas C. Zakas'"
    })
    public void filterBookItemsByAuthorTest(String searchQuery) {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.searchBooksBy(searchQuery);
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        Allure.step("Убедиться, что в каталоге найдены книги по критерию");
        assertThat(authors).allMatch(author -> author.contains(searchQuery));
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по издательству")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @EnumSource(Publisher.class)
    public void filterBookItemsByPublisherTest(Publisher publisher) {
        String publisherName = publisher.getName();
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.searchBooksBy(publisherName);
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        Allure.step("Убедиться, что в каталоге найдены книги по критерию");
        assertThat(publishers).allMatch(publisherItem -> publisherItem.contains(publisherName));
    }

    @ParameterizedTest
    @DisplayName("Изменение количества строк, отображаемых в каталоге")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @EnumSource(RowsCountOption.class)
    public void changeAmountOfRowsDisplayedTest(RowsCountOption option) {
        bookCatalogPage.openCatalogPage();
        String selectedOption = option.getOptionName();
        bookCatalogPage.changeAmountOfRowsOnCatalogPage(selectedOption);
        int maxRowsCount = bookStoreUiSteps.countMaxRows(selectedOption);
        int actualRowsCount = bookCatalogPage.countActualRows();
        Allure.step("Убедиться, что количество строк, отображаемых в каталоге, равно выбранному значению или меньше");
        assertTrue(actualRowsCount <= maxRowsCount, "Actual count should be less than or equal to max count");
    }
}