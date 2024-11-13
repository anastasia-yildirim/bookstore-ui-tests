package ui.steps;

import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

public class BrowserSteps {

    @Step("Перейти в другую вкладку")
    public void switchToAnotherTab(int tabIndex) {
        switchTo().window(tabIndex);
    }

    @Step("Открыть страницу в новой вкладке")
    public void openPageInAnotherTab(String pagePath, int tabIndex) {
        executeJavaScript("window.open('" + TestEnvironmentConfigurator.getBookStoreConfig().getBaseUrl() + pagePath
                + "', '_blank');");
        switchTo().window(tabIndex);
    }
}