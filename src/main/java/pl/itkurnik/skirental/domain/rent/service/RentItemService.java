package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.exception.RentItemNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentItemRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RentItemService {
    private final RentItemRepository rentItemRepository;
    private final RentItemStatusService rentItemStatusService;

    public RentItem findByRentIdAndItemId(Integer rentId, Integer itemId) {
        return rentItemRepository.findByRent_IdAndItem_Id(rentId, itemId)
                .orElseThrow(() -> new RentItemNotFoundException(rentId, itemId));
    }

    public void returnRentItem(ReturnRentItemRequest request, Instant finishDate) {
        RentItem rentItem = findByRentIdAndItemId(request.getRentId(), request.getItemId());

        rentItem.setRentedTo(finishDate);
        rentItem.setRentItemStatus(rentItemStatusService.getFinishedStatus());

        rentItemRepository.saveAndFlush(rentItem);
    }
}
