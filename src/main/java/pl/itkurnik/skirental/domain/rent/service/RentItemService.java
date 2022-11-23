package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.repository.RentItemRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentItemService {
    private final RentItemRepository rentItemRepository;
    private final RentItemStatusService rentItemStatusService;

    public List<RentItem> findAllByRentIdAndItemsIds(Integer rentId, List<Integer> itemsIds) {
        return rentItemRepository.findAllByRent_IdAndItem_IdIn(rentId, itemsIds);
    }

    public void returnRentItems(ReturnRentItemsRequest request) {
        List<RentItem> rentItems = findAllByRentIdAndItemsIds(request.getRentId(), request.getItemsIds());
        Instant returnDate = Instant.now();

        for (RentItem rentItem : rentItems) {
            rentItem.setRentedTo(returnDate);
            rentItem.setRentItemStatus(rentItemStatusService.getFinishedStatus());
        }

        rentItemRepository.saveAll(rentItems);
    }
}
