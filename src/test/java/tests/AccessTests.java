package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.ProfilePage;

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
        profilePage.checkNotLoggedInMessage();
    }
}