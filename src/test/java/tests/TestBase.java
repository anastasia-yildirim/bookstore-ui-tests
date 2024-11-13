package tests;

import config.TestEnvironmentConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.Attachments.*;
import static helpers.LoginExtension.clearSession;

public class TestBase {

    private static final TestEnvironmentConfigurator driver = new TestEnvironmentConfigurator();

    @BeforeAll
    public static void setUp() {
        driver.createWebDriver();
    }

    @AfterEach
    void shutDown() {
        generateDataForAllureReport();
        closeWebDriver();
        clearSession();
    }
}