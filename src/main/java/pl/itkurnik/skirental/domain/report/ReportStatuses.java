package pl.itkurnik.skirental.domain.report;

public enum ReportStatuses {
    OPEN("OPEN"),
    CLOSED("CLOSED");

    private final String name;

    ReportStatuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
