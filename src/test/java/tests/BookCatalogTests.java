package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BookCatalogPage;
import steps.ui.BookStoreUiSteps;
import testdata.enums.Publisher;
import testdata.enums.RowsCountOption;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("books")
@Epic("Книжный магазин DemoQA")
@Story("Действия пользователя в каталоге книг")
@Feature("Поиск, фильтрация и сортировка книг в каталоге")
public class BookCatalogTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
        List<String> sortedTitles = titles.stream().sorted().collect(Collectors.toList());
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = publishers.stream().sorted().collect(Collectors.toList());
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = authors.stream().sorted().collect(Collectors.toList());
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по названию в обратном алфавитном порядке")
    @Issue("Bug: the sorting order is wrong")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByTitleDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
        List<String> sortedTitles = titles.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по издательству в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByPublisherDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = publishers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    @DisplayName("Сортировка книг в каталоге по автору в обратном алфавитном порядке")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    public void sortBookItemsByAuthorDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = authors.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по названию")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"Git", "JavaScript", "Design", "Script", "ing"})
    public void filterBookItemsByTitleTest(String searchQuery) {
        bookStoreUiSteps.openBookCatalogPage();
        bookCatalogPage.getSearchBox().setValue(searchQuery);
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
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
        bookStoreUiSteps.openBookCatalogPage();
        bookCatalogPage.getSearchBox().setValue(searchQuery);
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        assertThat(authors).allMatch(author -> author.contains(searchQuery));
    }

    @ParameterizedTest
    @DisplayName("Поиск книг в каталоге по издательству")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @EnumSource(Publisher.class)
    public void filterBookItemsByPublisherTest(Publisher publisher) {
        String publisherName = publisher.getName();
        bookStoreUiSteps.openBookCatalogPage();
        bookCatalogPage.getSearchBox().setValue(publisherName);
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        assertThat(publishers).allMatch(publisherItem -> publisherItem.contains(publisherName));
    }

    @ParameterizedTest
    @DisplayName("Изменение количества строк, отображаемых в каталоге")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.NORMAL)
    @EnumSource(RowsCountOption.class)
    public void changeAmountOfRowsDisplayedTest(RowsCountOption option) {
        bookStoreUiSteps.openBookCatalogPage();
        String selectedOption = option.getOptionName();
        bookCatalogPage.getRowsCountOptionButton().selectOption(selectedOption);
        int maxRowsCount = Integer.parseInt(selectedOption.replaceAll("[^0-9]", ""));
        int actualRowsCount = bookCatalogPage.getRows().size();
        assertTrue(actualRowsCount <= maxRowsCount, "Actual count should be less than or equal to max count");
    }
}