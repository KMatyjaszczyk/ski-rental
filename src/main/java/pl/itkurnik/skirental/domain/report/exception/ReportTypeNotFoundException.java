package pl.itkurnik.skirental.domain.report.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class ReportTypeNotFoundException extends ObjectNotFoundException {

    public ReportTypeNotFoundException(Integer id) {
        super(String.format("Report type with id %d nto found", id));
    }
}
