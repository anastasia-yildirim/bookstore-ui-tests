package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Книжный магазин DemoQA")
@Feature("Авторизация пользователя")
@Story("Доступ к страницам неавторизованного пользователя")

public class AccessTests extends TestBase {

    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Неуспешная попытка неавторизованного пользователя перейти в профиль")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldShowNotLoggedInMessageWhenVisitingProfileWithoutLoginTest() {
        profilePage.openProfilePage();
        Allure.step("Убедиться, отображается сообщение " + profilePage.getNotLoggedInText());
        assertThat(profilePage.getNotLoggedInMessageDisplayed()).isEqualTo(profilePage.getNotLoggedInText());
    }
}