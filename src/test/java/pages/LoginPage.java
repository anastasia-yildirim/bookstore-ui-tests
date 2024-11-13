package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Data
public class LoginPage {
    private String path = "/login";

    private SelenideElement userName = $("#userName");
    private SelenideElement passwordElement = $("#password");
    private SelenideElement loginButton = $("#login");
    private SelenideElement validationMessage = $("#name");
    private SelenideElement pageTitle = $(".text-center");
    private SelenideElement loggedInUserNameValue = $("#userName-value");
    private SelenideElement loggedInMessage = $("#loading-label");
    private SelenideElement profileLink = $("#loading-label a");

    private String expectedValidationMessage = "Invalid username or password!";
    private String expectedPageTitle = "Login";
    private String expectedLoggedInMessage = "You are already logged in. View your profile.";

    @Step("Открыть страницу авторизации")
    public void openLoginPage() {
        open(path);
    }

    @Step("Ввести логин")
    public void enterUserName(String login) {
        userName.sendKeys(login);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordElement.sendKeys(password);
    }

    @Step("Нажать на кнопку Login")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Получить отобразившееся валидационное сообщение")
    public String getValidationMessageText() {

        return validationMessage.getText();
    }

    @Step("Перейти в профиль со страницы авторизации (пользователь авторизован)")
    public void goToProfileFromLoginPageWhileLoggedIn() {
        profileLink.shouldBe(visible).click();
    }

    @Step("Получить название отобразившейся страницы")
    public String getDisplayedPageTitle() {

        return pageTitle.getText();
    }
}