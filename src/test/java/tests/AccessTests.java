package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import steps.ui.BookStoreUiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("authorization")
@Epic("Книжный магазин DemoQA")
@Story("Авторизация пользователя")
@Feature("Доступ к страницам неавторизованного пользователя")
public class AccessTests extends TestBase {

    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Неуспешная попытка неавторизованного пользователя перейти в профиль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldShowNotLoggedInMessageWhenVisitingProfileWithoutLoginTest() {
        bookStoreUiSteps.openProfilePage();
        assertThat(bookStoreUiSteps.getNotLoggedInMessageDisplayed()).isEqualTo(profilePage.getNotLoggedInText());
    }
}
