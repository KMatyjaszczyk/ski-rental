package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.ItemStatuses;
import pl.itkurnik.skirental.domain.item.exception.ItemStatusNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.ItemStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatusService {
    private final ItemStatusRepository itemStatusRepository;

    public ItemStatus findById(Integer id) {
        return itemStatusRepository.findById(id)
                .orElseThrow(() -> new ItemStatusNotFoundException(id));
    }

    public List<ItemStatus> findAll() {
        return itemStatusRepository.findAll();
    }

    public ItemStatus getOpenStatus() {
        return findByName(ItemStatuses.OPEN.getName());
    }

    public ItemStatus getRentedStatus() {
        return findByName(ItemStatuses.RENTED.getName());
    }

    public ItemStatus getBrokenStatus() {
        return findByName(ItemStatuses.BROKEN.getName());
    }

    public ItemStatus getInServiceStatus() {
        return findByName(ItemStatuses.IN_SERVICE.getName());
    }

    private ItemStatus findByName(String name) {
        return itemStatusRepository.findByName(name)
                .orElseThrow(() -> new ItemStatusNotFoundException(name));
    }
}
