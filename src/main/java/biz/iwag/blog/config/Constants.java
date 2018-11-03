package biz.iwag.blog.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final String LAST_API_VERSION = "versions:last_api_version";
    public static final String LAST_BUILD_VERSION = "versions:last_build_number";
    public static final String LAST_APP_VERSION = "versions:last_application_version";

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no_liquibase";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    private Constants() {
    }
}
