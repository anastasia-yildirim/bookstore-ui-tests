package config;

@TestEnvConfig.LoadPolicy(TestEnvConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "classpath:base.properties",
        "classpath:remote.properties"
})
public interface TestEnvConfig extends org.aeonbits.owner.Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserVersion")
    @DefaultValue("latest")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1280x1024")
    String browserSize();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();

    @Key("selenoidUser")
    String selenoidUser();

    @Key("selenoidPassword")
    String selenoidPassword();

    @Key("selenoidHost")
    String selenoidHost();

    @Key("remoteUrl")
    String getRemoteUrl();
}