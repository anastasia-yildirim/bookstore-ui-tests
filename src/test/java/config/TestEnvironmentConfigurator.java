package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestEnvironmentConfigurator {

    @Getter
    private static final TestEnvConfig testEnvConfig = ConfigFactory.create(TestEnvConfig.class, System.getProperties());
    @Getter
    private static final BookStoreConfig bookStoreConfig = ConfigFactory.create(BookStoreConfig.class, System.getProperties());

    public TestEnvironmentConfigurator() {
        processConfig();
    }

    public void processConfig() {
        Configuration.baseUrl = bookStoreConfig.getBaseUrl();
        RestAssured.baseURI = bookStoreConfig.getBaseUrl();

        Configuration.browser = testEnvConfig.browserName();
        Configuration.browserVersion = testEnvConfig.browserVersion();
        Configuration.browserSize = testEnvConfig.browserSize();

        Configuration.pageLoadStrategy = "eager";

        if (testEnvConfig.isRemote()) {
            Configuration.remote = testEnvConfig.getRemoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true));

            Configuration.browserCapabilities = capabilities;
        }
    }
}