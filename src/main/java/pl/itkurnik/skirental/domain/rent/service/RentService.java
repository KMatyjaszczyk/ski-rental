package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.service.ItemStatusChanger;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final RentCreator rentCreator;
    private final RentItemsCreator rentItemsCreator;
    private final ItemStatusChanger itemStatusChanger;
    private final RentItemsReturner rentItemsReturner;
    private final RentFinisher rentFinisher;
    private final RentItemService rentItemService;

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Rent findById(Integer id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public List<Rent> findAllByClientId(Integer clientId) {
        return rentRepository.findAllByClient_Id(clientId);
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
    public void returnItem(ReturnRentItemRequest request) {
        Instant finishDate = Instant.now();

        returnItem(request, finishDate);
        rentFinisher.finishRentIfNecessary(request.getRentId(), finishDate);
    }

    private void returnItem(ReturnRentItemRequest request, Instant finishDate) {
        rentItemsReturner.returnItem(request, finishDate);
        itemStatusChanger.changeToOpen(request.getItemId());
    }

    @Transactional
    public void finishRentById(Integer rentId) {
        Instant finishDate = Instant.now();

        returnAllRentedItems(rentId, finishDate);
        rentFinisher.finishRentIfNecessary(rentId, finishDate);
    }

    private void returnAllRentedItems(Integer rentId, Instant finishDate) {
        List<Integer> rentedItemsIds = receiveRentedItemsIds(rentId);

        rentItemsReturner.returnAllRentedItems(rentId, finishDate);
        itemStatusChanger.changeAllToOpen(rentedItemsIds);
    }

    private List<Integer> receiveRentedItemsIds(Integer rentId) {
        return rentItemService.findAllRentedRentItems(rentId)
                .stream()
                .map(rentItem -> rentItem.getItem().getId())
                .collect(Collectors.toList());
    }
}
