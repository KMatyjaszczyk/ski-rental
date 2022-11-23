package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.service.ItemStatusChanger;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final RentCreator rentCreator;
    private final RentItemsCreator rentItemsCreator;
    private final ItemStatusChanger itemStatusChanger;
    private final RentItemsReturner rentItemsReturner;

    public Rent findById(Integer id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

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

    @Transactional
    public void returnItems(ReturnRentItemsRequest request) {
        rentItemsReturner.returnItems(request);
        
        changeItemsStatusToOpen(request.getItemsIds());
    }

    private void changeItemsStatusToOpen(List<Integer> itemsIds) {
        itemStatusChanger.changeAllToOpen(itemsIds);
    }
}
