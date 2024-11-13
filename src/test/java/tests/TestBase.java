package tests;

import config.TestEnvironmentConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.BookCatalogPage;
import steps.ui.BookStoreUiSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.Attachments.*;
import static helpers.LoginExtension.clearSession;

public class TestBase {

    private static final TestEnvironmentConfigurator driver = new TestEnvironmentConfigurator();

    protected static BookCatalogPage bookCatalogPage;
    protected static BookStoreUiSteps bookStoreUiSteps;

    @BeforeAll
    public static void setUp() {
        driver.createWebDriver();
        bookCatalogPage = new BookCatalogPage();
        bookStoreUiSteps = new BookStoreUiSteps();
    }

    @AfterEach
    void shutDown() {
        generateDataForAllureReport();
        closeWebDriver();
        clearSession();
    }
}