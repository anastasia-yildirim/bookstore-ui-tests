package tests;

import io.qameta.allure.Issue;
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

public class BookCatalogTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    BookCatalogPage bookCatalogPage = new BookCatalogPage();

    @Issue("Bug: the sorting order is wrong")
    @Test
    public void sortBookItemsByTitleAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
        List<String> sortedTitles = titles.stream().sorted().collect(Collectors.toList());
        //Assert
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    public void sortBookItemsByPublisherAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = publishers.stream().sorted().collect(Collectors.toList());
        //Assert
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    public void sortBookItemsByAuthorAscendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = authors.stream().sorted().collect(Collectors.toList());
        //Assert
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @Issue("Bug: the sorting order is wrong")
    @Test
    public void sortBookItemsByTitleDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getTitleButton());
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
        List<String> sortedTitles = titles.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //Assert
        assertThat(titles).isEqualTo(sortedTitles);
    }

    @Test
    public void sortBookItemsByPublisherDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getPublisherButton());
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        List<String> sortedPublishers = publishers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //Assert
        assertThat(publishers).isEqualTo(sortedPublishers);
    }

    @Test
    public void sortBookItemsByAuthorDescendingTest() {
        bookStoreUiSteps.openBookCatalogPage();
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        bookStoreUiSteps.sortBookItemsBy(bookCatalogPage.getAuthorButton());
        List<String> authors = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getAuthorCells());
        List<String> sortedAuthors = authors.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(authors).isEqualTo(sortedAuthors);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Git", "JavaScript", "Design", "Script", "ing"})
    public void filterBookItemsByTitleTest(String searchQuery) {
        bookStoreUiSteps.openBookCatalogPage();
        bookCatalogPage.getSearchBox().setValue(searchQuery);
        List<String> titles = bookCatalogPage.getTitleTexts().texts();
        assertThat(titles).allMatch(title -> title.contains(searchQuery));
    }

    @ParameterizedTest
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
    @EnumSource(Publisher.class)
    public void filterBookItemsByPublisherTest(Publisher publisher) {
        String publisherName = publisher.getName();
        bookStoreUiSteps.openBookCatalogPage();
        bookCatalogPage.getSearchBox().setValue(publisherName);
        List<String> publishers = bookStoreUiSteps.getTextFromCells(bookCatalogPage.getPublisherCells());
        assertThat(publishers).allMatch(publisherItem -> publisherItem.contains(publisherName));
    }

    @ParameterizedTest
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