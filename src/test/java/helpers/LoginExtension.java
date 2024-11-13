package helpers;

import api.models.Session;
import api.models.login.LoginResponseModel;
import api.steps.BookStoreAuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtension implements BeforeEachCallback {

    private static final ThreadLocal<Session> session = new ThreadLocal<>();

    public static Session getSession() {
        return session.get();
    }

    public static void clearSession() {
        session.remove();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Session newSession = new Session();
        LoginResponseModel authResponse = BookStoreAuthorizationApi.getAuthorization();
        newSession.setUserId(authResponse.getUserId());
        newSession.setToken(authResponse.getToken());
        newSession.setExpires(authResponse.getExpires());
        session.set(newSession);

        BookStoreAuthorizationApi.buildAuthorizationCookie(session.get());
    }
}