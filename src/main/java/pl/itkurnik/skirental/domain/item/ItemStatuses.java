package pl.itkurnik.skirental.domain.item;

public enum ItemStatuses {
    OPEN("OPEN"),
    RENTED("RENTED"),
    BROKEN("BROKEN"),
    IN_SERVICE("IN SERVICE");

    private final String name;

    ItemStatuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
