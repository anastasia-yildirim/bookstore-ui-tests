package steps.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.TestEnvironmentConfigurator;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class BookStoreUiSteps {

    @Step("Перейти в другую вкладку")
    public void switchToAnotherTab(int tabIndex) {
        switchTo().window(tabIndex);
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

    @Step("Отсортировать список полученных строк в алфавитном порядке")
    public List<String> sortAscending(List<String> items) {

        return items.stream().sorted().collect(Collectors.toList());
    }

    @Step("Отсортировать список полученных строк в обратном алфавитном порядке")
    public List<String> sortDescending(List<String> items) {

        return items.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    @Step("Посчитать максимально возможное количество строк")
    public int countMaxRows(String selectedOption) {

        return Integer.parseInt(selectedOption.replaceAll("[^0-9]", ""));
    }
}