package pl.itkurnik.skirental.domain.report.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class ReportStatusNotFoundException extends ObjectNotFoundException {

    public ReportStatusNotFoundException(String name) {
        super(String.format("Report status with name %s not found", name));
    }
}
