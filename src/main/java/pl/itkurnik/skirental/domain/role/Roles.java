package pl.itkurnik.skirental.domain.role;

public enum Roles {
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private final String name;
    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
