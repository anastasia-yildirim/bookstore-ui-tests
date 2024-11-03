package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class LoginPage {
    private SelenideElement userName = $("#userName");
    private SelenideElement password = $("#password");
    private SelenideElement loginButton = $("#login");
    private SelenideElement validationMessage = $("#name");
    private SelenideElement pageTitle = $(".text-center");

    private String expectedValidationMessage = "Invalid username or password!";
    private String expectedPageTitle = "Login";
}