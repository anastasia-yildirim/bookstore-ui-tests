package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class LoginPage {
    private String path = "/login";

    private SelenideElement userName = $("#userName");
    private SelenideElement password = $("#password");
    private SelenideElement loginButton = $("#login");
    private SelenideElement validationMessage = $("#name");
    private SelenideElement pageTitle = $(".text-center");
    private SelenideElement loggedInUserNameValue = $("#userName-value");
    private SelenideElement loggedInMessage = $("#loading-label");
    private SelenideElement profileLink = $("#loading-label a");

    private String expectedValidationMessage = "Invalid username or password!";
    private String expectedPageTitle = "Login";
    private String expectedLoggedInMessage = "You are already logged in. View your profile.";
}