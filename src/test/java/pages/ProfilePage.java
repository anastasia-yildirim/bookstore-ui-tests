package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class ProfilePage {

    private String notLoggedInText = "Currently you are not logged into the Book Store application, please visit " +
            "the login page to enter or register page to register yourself.";
    private SelenideElement notLoggedInLabel = $("#notLoggin-label");
}
