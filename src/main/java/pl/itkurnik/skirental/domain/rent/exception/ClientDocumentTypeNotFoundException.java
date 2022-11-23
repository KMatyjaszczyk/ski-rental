package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class ClientDocumentTypeNotFoundException extends ObjectNotFoundException {

    public ClientDocumentTypeNotFoundException(Integer id) {
        super(String.format("Client document type with id %d not found", id));
    }
}
