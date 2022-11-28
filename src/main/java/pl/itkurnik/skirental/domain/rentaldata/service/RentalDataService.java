package pl.itkurnik.skirental.domain.rentaldata.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rentaldata.RentalData;
import pl.itkurnik.skirental.domain.rentaldata.RentalDataRepository;
import pl.itkurnik.skirental.domain.rentaldata.dto.UpdateRentalDataRequest;
import pl.itkurnik.skirental.domain.rentaldata.exception.RentalDataNotFoundException;
import pl.itkurnik.skirental.domain.rentaldata.validation.UpdateRentalDataValidator;

@Service
@RequiredArgsConstructor
public class RentalDataService {
    private final RentalDataRepository rentalDataRepository;
    private final UpdateRentalDataValidator updateRentalDataValidator;

    public void update(UpdateRentalDataRequest request) {
        updateRentalDataValidator.validateFields(request);

        RentalData rentalData = getRentalData();
        updateProperFields(rentalData, request);

        rentalDataRepository.save(rentalData);
    }

    private RentalData getRentalData() {
        return rentalDataRepository.findAll().stream()
                .findFirst()
                .orElseThrow(RentalDataNotFoundException::new);
    }

    private void updateProperFields(RentalData rentalData, UpdateRentalDataRequest request) {
        if (!StringUtils.equals(request.getCompanyName(), rentalData.getCompanyName())) {
            rentalData.setCompanyName(request.getCompanyName());
        }

        if (!StringUtils.equals(request.getTaxId(), rentalData.getTaxId())) {
            rentalData.setTaxId(request.getTaxId());
        }

        if (!StringUtils.equals(request.getRegon(), rentalData.getRegon())) {
            rentalData.setRegon(request.getRegon());
        }

        if (!StringUtils.equals(request.getEmailAddress(), rentalData.getEmailAddress())) {
            rentalData.setEmailAddress(request.getEmailAddress());
        }

        if (!StringUtils.equals(request.getCity(), rentalData.getCity())) {
            rentalData.setCity(request.getCity());
        }

        if (!StringUtils.equals(request.getPostCode(), rentalData.getPostCode())) {
            rentalData.setPostCode(request.getPostCode());
        }

        if (!StringUtils.equals(request.getStreet(), rentalData.getStreet())) {
            rentalData.setStreet(request.getStreet());
        }

        if (!StringUtils.equals(request.getHouseNumber(), rentalData.getHouseNumber())) {
            rentalData.setHouseNumber(request.getHouseNumber());
        }

        if (!StringUtils.equals(request.getDescription(), rentalData.getDescription())) {
            rentalData.setDescription(request.getDescription());
        }
    }
}
