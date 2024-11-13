package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Data
public class ProfilePage {

    private final String notLoggedInText = "Currently you are not logged into the Book Store application, please visit " +
            "the login page to enter or register page to register yourself.";
    private String path = "/profile";
    private SelenideElement notLoggedInLabel = $("#notLoggin-label");
    private SelenideElement closeModalButton = $("#closeSmallModal-ok");
    private SelenideElement deleteRecordButton = $("#delete-record-undefined");
    private SelenideElement bookInProfile;

    private SelenideElement getSpecificBookInProfile(String isbn) {
        bookInProfile = $(".ReactTable").$("a[href='/profile?book=" + isbn + "']");
        return bookInProfile;
    }

    @Step("Открыть страницу профиля")
    public void openProfilePage() {
        open(path);
    }

    public SelenideElement getBookInProfile(String isbn) {

        return $(".ReactTable").$("a[href='/profile?book=" + isbn + "']");
    }

    @Step("Удалить книгу из профиля")
    public void deleteBookFromProfile(String isbn) {
        getSpecificBookInProfile(isbn).shouldBe(visible);
        deleteRecordButton.scrollTo().click();
        closeModalButton.click();
    }

    @Step("Убедиться, отображается сообщение " + notLoggedInText)
    public void checkNotLoggedInMessage() {
        assertThat(notLoggedInLabel.getText()).isEqualTo(notLoggedInText);
    }
}
