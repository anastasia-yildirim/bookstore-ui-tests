package config;

@TestEnvConfig.LoadPolicy(TestEnvConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "classpath:base.properties",
        "classpath:auth.properties"
})
public interface BookStoreConfig extends org.aeonbits.owner.Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("login")
    String login();

    @Key("password")
    String password();

}
