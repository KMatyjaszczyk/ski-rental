package pl.itkurnik.skirental.domain.report.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class ReportNotFoundException extends ObjectNotFoundException {

    public ReportNotFoundException(Integer id) {
        super(String.format("Report with id %d not found", id));
    }
}
