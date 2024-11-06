package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Книжный магазин DemoQA")
@Feature("Авторизация пользователя")
@Story("Доступ к страницам неавторизованного пользователя")

public class AccessTests extends TestBase {

    private final BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Неуспешная попытка неавторизованного пользователя перейти в профиль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldShowNotLoggedInMessageWhenVisitingProfileWithoutLoginTest() {
        bookStoreUiSteps.openPage(profilePage.getPath());
        Allure.step("Убедиться, отображается сообщение " + profilePage.getNotLoggedInText());
        assertThat(bookStoreUiSteps.getNotLoggedInMessageDisplayed()).isEqualTo(profilePage.getNotLoggedInText());
    }
}