package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
}