package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.ClientDocumentType;
import pl.itkurnik.skirental.domain.rent.dto.CreateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.dto.UpdateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.exception.ClientDocumentTypeNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.ClientDocumentTypeRepository;
import pl.itkurnik.skirental.domain.rent.validation.CreateClientDocumentTypeValidator;
import pl.itkurnik.skirental.domain.rent.validation.UpdateClientDocumentTypeValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientDocumentTypeService {
    private final ClientDocumentTypeRepository clientDocumentTypeRepository;
    private final CreateClientDocumentTypeValidator createClientDocumentTypeValidator;
    private final UpdateClientDocumentTypeValidator updateClientDocumentTypeValidator;

    public List<ClientDocumentType> findAll() {
        return clientDocumentTypeRepository.findAll();
    }

    public ClientDocumentType findById(Integer id) {
        return clientDocumentTypeRepository.findById(id)
                .orElseThrow(() -> new ClientDocumentTypeNotFoundException(id));
    }

    public void deleteById(Integer id) {
        try {
            clientDocumentTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Client document type with ID {} already deleted", id);
        }
    }

    public void create(CreateClientDocumentTypeRequest request) {
        createClientDocumentTypeValidator.validate(request);

        createClientDocumentType(request);
    }

    private void createClientDocumentType(CreateClientDocumentTypeRequest request) {
        ClientDocumentType clientDocumentType = new ClientDocumentType();
        clientDocumentType.setName(request.getName());
        clientDocumentType.setDescription(request.getDescription());

        clientDocumentTypeRepository.save(clientDocumentType);
    }

    public void update(UpdateClientDocumentTypeRequest request) {
        updateClientDocumentTypeValidator.validateFields(request);

        ClientDocumentType clientDocumentType = clientDocumentTypeRepository.findById(request.getId())
                .orElseThrow(() -> new ClientDocumentTypeNotFoundException(request.getId()));
        updateProperFields(clientDocumentType, request);

        clientDocumentTypeRepository.save(clientDocumentType);
    }

    private void updateProperFields(ClientDocumentType clientDocumentType, UpdateClientDocumentTypeRequest request) {
        if (!StringUtils.equals(request.getName(), clientDocumentType.getName())) {
            clientDocumentType.setName(request.getName());
        }

        if (!StringUtils.equals(request.getDescription(), clientDocumentType.getDescription())) {
            clientDocumentType.setDescription(request.getDescription());
        }
    }
}
