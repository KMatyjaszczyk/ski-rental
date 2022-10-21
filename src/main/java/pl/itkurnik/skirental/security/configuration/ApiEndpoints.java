package pl.itkurnik.skirental.security.configuration;

class ApiEndpoints {
    public static final String[] PUBLIC = {
            "/api/auth/*",
            "/api/user/*", // TODO KM for tests only
            "/api/user", // TODO KM for tests only
            "/api/user_role/*", // TODO KM for test only
            "/api/manufacturer", // TODO KM for test only
            "/api/manufacturer/*", // TODO KM for test only
            "/api/equipment_category", // TODO KM for test only
            "/api/equipment_category/*", // TODO KM for test only
            "/api/equipment", // TODO KM for test only
            "/api/equipment/**", // TODO KM for test only
            "/api/size", // TODO KM for test only
            "/api/size/**", // TODO KM for test only
            "/api/item_status", // TODO KM for test only
            "/api/item_status/**" // TODO KM for test only
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
