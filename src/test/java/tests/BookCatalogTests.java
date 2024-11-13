package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import testdata.enums.Publisher;
import testdata.enums.RowsCountOption;
import ui.pages.BookCatalogPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Книжный магазин DemoQA")
@Feature("Действия пользователя в каталоге книг")
@Story("Поиск, фильтрация и сортировка книг в каталоге")
public class BookCatalogTests extends TestBase {

    private final BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByTitle();
        bookCatalogPage.checkBooksAreSortedAscendingByTitle();
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByPublisher();
        bookCatalogPage.checkBooksAreSortedAscendingByPublisher();
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorAscendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByAuthor();
        bookCatalogPage.checkBooksAreSortedAscendingByAuthor();
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в обратном алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByTitle();
        bookCatalogPage.sortBookItemsByTitle();
        bookCatalogPage.checkBooksAreSortedDescendingByTitle();
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByPublisher();
        bookCatalogPage.sortBookItemsByPublisher();
        bookCatalogPage.checkBooksAreSortedDescendingByPublisher();
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorDescendingTest() {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.sortBookItemsByAuthor();
        bookCatalogPage.sortBookItemsByAuthor();
        bookCatalogPage.checkBooksAreSortedDescendingByAuthor();
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по названию")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"Git", "JavaScript", "Design", "Script", "ing"})
    public void filterBookItemsByTitleTest(String searchQuery) {
        bookCatalogPage.openCatalogPage();
        bookCatalogPage.searchBooksBy(searchQuery);
        bookCatalogPage.checkBooksAreFilteredByTitle(searchQuery);
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
        bookCatalogPage.checkBooksAreFilteredByAuthor(searchQuery);
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
        bookCatalogPage.checkBooksAreFilteredByPublisher(publisherName);
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
        int maxRowsCount = bookCatalogPage.countMaxRows(selectedOption);
        int actualRowsCount = bookCatalogPage.countActualRows();
        Allure.step("Убедиться, что количество строк, отображаемых в каталоге, равно выбранному значению или меньше");
        assertTrue(actualRowsCount <= maxRowsCount, "Actual count should be less than or equal to max count");
    }
}