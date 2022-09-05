package pl.itkurnik.skirental.security.util;

public class ApiEndpoints {
    public static final String[] PUBLIC = {
            "/api/auth/*"
    };

    public static final String[] EMPLOYEE = {
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
