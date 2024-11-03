package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class BookCatalogPage {

    private SelenideElement userNameValue = $("#userName-value");
    private SelenideElement logoutButton = $("#submit");
}