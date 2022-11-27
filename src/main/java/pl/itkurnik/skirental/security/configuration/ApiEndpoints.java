package pl.itkurnik.skirental.security.configuration;

class ApiEndpoints {
    public static final String[] PUBLIC = {
            "/api/auth/*",
            // TODO KM all below
            "/api/user/*",
            "/api/user",
            "/api/user_role/*",
            "/api/manufacturer",
            "/api/manufacturer/*",
            "/api/equipment_category",
            "/api/equipment_category/**",
            "/api/equipment",
            "/api/equipment/**",
            "/api/size",
            "/api/size/**",
            "/api/item_status",
            "/api/item_status/**",
            "/api/item",
            "/api/item/**",
            "/api/price",
            "/api/price/**",
            "/api/client_document_type",
            "/api/client_document_type/**",
            "/api/rent_status/**",
            "/api/equipment_image/**",
            "/api/rent/**",
            "/api/payment_method/**",
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
