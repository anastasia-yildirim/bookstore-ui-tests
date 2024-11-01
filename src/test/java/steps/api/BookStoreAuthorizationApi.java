package steps.api;

import models.Session;
import io.qameta.allure.Step;
import models.bookstore.request.GenerateTokenRequestModel;
import models.bookstore.response.GenerateTokenResponseModel;
import models.bookstore.request.LoginRequestModel;
import models.bookstore.response.LoginResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;

import config.Config;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.BookStoreSpec.*;

public class BookStoreAuthorizationApi {

    private static final Config config = ConfigFactory.create(Config.class);

    @Step("Сгенерировать токен")
    public static void generateToken() {
        GenerateTokenRequestModel bodyData = new GenerateTokenRequestModel();
        bodyData.setUserName(config.login());
        bodyData.setPassword(config.password());

        GenerateTokenResponseModel response =
                given(bookStoreRequestSpec)
                        .body(bodyData)
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .spec(bookStoreResponseSpec200)
                        .extract().as(GenerateTokenResponseModel.class);

            assertEquals("Success", response.getStatus());
            assertEquals("User authorized successfully.", response.getResult());
    }

    @Step("Залогиниться")
    public static LoginResponseModel getAuthorization() {

        generateToken();

        LoginRequestModel bodyData = new LoginRequestModel();
        bodyData.setUserName(config.login());
        bodyData.setPassword(config.password());

        return given(bookStoreRequestSpec)
                .body(bodyData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(bookStoreResponseSpec200)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Сгенерировать куки для браузера")
    public static void buildAuthorizationCookie(Session session) {
        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userID", session.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", session.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", session.getExpires()));
    }
}