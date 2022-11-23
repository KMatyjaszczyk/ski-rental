package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.ClientDocumentType;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.RentStatus;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;
import pl.itkurnik.skirental.domain.rent.validation.create.CreateRentValidator;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.service.UserService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class RentCreator {
    private final CreateRentValidator createRentValidator;
    private final RentRepository rentRepository;
    private final UserService userService;
    private final RentStatusService rentStatusService;
    private final ClientDocumentTypeService clientDocumentTypeService;

    public Rent createFromRequest(CreateRentRequest request, Instant createTime) {
        createRentValidator.validateRequest(request);

        Rent rent = createRent(request, createTime);
        return rentRepository.save(rent);
    }

    private Rent createRent(CreateRentRequest request, Instant createTime) {
        User client = userService.findById(request.getClientId());
        RentStatus rentedStatus = rentStatusService.getRentedStatus();
        ClientDocumentType clientDocumentType = clientDocumentTypeService.findById(request.getClientDocumentTypeId());
        User employee = userService.findById(request.getEmployeeId());

        return Rent.builder()
                .rentDate(createTime)
                .plannedReturnDate(request.getPlannedReturnDate())
                .client(client)
                .rentStatus(rentedStatus)
                .clientDocumentType(clientDocumentType)
                .clientDocumentNumber(request.getClientDocumentNumber())
                .employee(employee)
                .description(request.getDescription())
                .build();
    }
}
