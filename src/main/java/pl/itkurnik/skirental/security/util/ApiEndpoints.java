package pl.itkurnik.skirental.security.util;

public class ApiEndpoints {
    public static final String[] PUBLIC = {
            "/api/auth/*",
            "/api/user/*", // TODO KM for tests only, it should be only for employee
            "/api/user" // TODO KM for tests only, it should be only for employee
    };

    public static final String[] EMPLOYEE = {
            "/api/test",
            "/api/test*"
    };

    public static final String[] SWAGGER = {
            "/api-docs/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };
}
