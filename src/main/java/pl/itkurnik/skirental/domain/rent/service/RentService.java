package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.item.service.ItemStatusService;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentCreator rentCreator;
    private final RentItemsCreator rentItemsCreator;
    private final ItemService itemService;
    private final ItemStatusService itemStatusService;

    @Transactional
    public void create(CreateRentRequest request) {
        createRentWithItems(request);

        changeItemsStatusToRented(request);
    }

    private void createRentWithItems(CreateRentRequest request) {
        Instant createTime = Instant.now();

        Rent rent = rentCreator.fromRequest(request, createTime);
        rentItemsCreator.fromRequest(request.getRentItemsIds(), rent, createTime);
    }

    private void changeItemsStatusToRented(CreateRentRequest request) {
        ItemStatus rentedStatus = itemStatusService.getRentedStatus();

        itemService.modifyItemsStatusByIds(request.getRentItemsIds(), rentedStatus);
    }
}
