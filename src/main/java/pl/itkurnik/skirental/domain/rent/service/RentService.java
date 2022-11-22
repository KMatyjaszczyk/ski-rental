package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.service.ItemStatusChanger;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentCreator rentCreator;
    private final RentItemsCreator rentItemsCreator;
    private final ItemStatusChanger itemStatusChanger;

    @Transactional
    public void create(CreateRentRequest request) {
        createRentWithItems(request);

        changeItemsStatusToRented(request.getRentItemsIds());
    }

    private void createRentWithItems(CreateRentRequest request) {
        Instant createTime = Instant.now();

        Rent rent = rentCreator.createFromRequest(request, createTime);
        rentItemsCreator.createFromItemsByIds(request.getRentItemsIds(), rent, createTime);
    }

    private void changeItemsStatusToRented(List<Integer> itemsIds) {
        itemStatusChanger.changeAllToRented(itemsIds);
    }
}
