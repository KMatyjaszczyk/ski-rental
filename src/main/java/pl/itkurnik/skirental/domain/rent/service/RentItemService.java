package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.RentItemStatuses;
import pl.itkurnik.skirental.domain.rent.exception.RentItemNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentItemRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentItemService {
    private final RentItemRepository rentItemRepository;
    private final RentItemStatusService rentItemStatusService;

    public List<RentItem> findAllRentedRentItems(Integer rentId) {
        List<RentItem> rentItems = rentItemRepository.findAllByRent_Id(rentId);

        return rentItems.stream()
                .filter(this::isRentItemRented)
                .collect(Collectors.toList());
    }

    private boolean isRentItemRented(RentItem rentItem) {
        return rentItem.getRentItemStatus().getName().equals(
                RentItemStatuses.RENTED.getName());
    }

    public RentItem findByRentIdAndItemId(Integer rentId, Integer itemId) {
        return rentItemRepository.findByRent_IdAndItem_Id(rentId, itemId)
                .orElseThrow(() -> new RentItemNotFoundException(rentId, itemId));
    }

    public void returnSingleRentItem(Integer rentItemId, Instant finishDate) {
        RentItem rentItem = findById(rentItemId);

        RentItem returnedRentItem = mapToReturned(rentItem, finishDate);

        rentItemRepository.saveAndFlush(returnedRentItem);
    }

    private RentItem findById(Integer id) {
        return rentItemRepository.findById(id)
                .orElseThrow(() -> new RentItemNotFoundException(id));
    }

    private RentItem mapToReturned(RentItem rentItem, Instant finishDate) {
        rentItem.setRentedTo(finishDate);
        rentItem.setRentItemStatus(rentItemStatusService.getFinishedStatus());

        return rentItem;
    }

    public void returnMultipleRentItems(List<Integer> rentItemsIds, Instant finishDate) {
        List<RentItem> rentedItems = rentItemRepository.findAllById(rentItemsIds);

        List<RentItem> newlyReturnedRentItems = rentedItems.stream()
                .map(rentItem -> mapToReturned(rentItem, finishDate))
                .collect(Collectors.toList());

        rentItemRepository.saveAllAndFlush(newlyReturnedRentItems);
    }
}
