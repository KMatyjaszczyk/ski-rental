package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.service.EquipmentService;
import pl.itkurnik.skirental.domain.equipment.service.SizeService;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.CreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.CreatePriceRequest;
import pl.itkurnik.skirental.domain.item.dto.PriceForCreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.UpdateItemRequest;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.ItemRepository;
import pl.itkurnik.skirental.domain.item.repository.PriceRepository;
import pl.itkurnik.skirental.domain.item.validation.CreateItemValidator;
import pl.itkurnik.skirental.domain.item.validation.UpdateItemValidator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;
    private final ItemPricesService itemPricesService;
    private final EquipmentService equipmentService;
    private final SizeService sizeService;
    private final ItemStatusService itemStatusService;
    private final CreateItemValidator createItemValidator;
    private final UpdateItemValidator updateItemValidator;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public List<Item> findAllByEquipmentId(Integer equipmentId) {
        return itemRepository.findAllByEquipment_Id(equipmentId);
    }

    public List<Item> findAllBySizeId(Integer sizeId) {
        return itemRepository.findAllBySize_Id(sizeId);
    }

    @Transactional
    public void deleteById(Integer id) {
        boolean itemDoesNotExist = !itemRepository.existsById(id);
        if (itemDoesNotExist) {
            return;
        }

        deleteItemWithPrices(id);
    }

    private void deleteItemWithPrices(Integer id) {
        priceRepository.deleteAllByItem_Id(id);
        itemRepository.deleteById(id);
    }

    @Transactional
    public void create(CreateItemRequest request) {
        createItemValidator.validateFields(request);
        createItemValidator.validateIfSizeIsInProperEquipmentCategory(request);

        Item item = createItem(request);
        createDefaultPriceForItem(request.getDefaultPrice(), item);
    }

    private Item createItem(CreateItemRequest request) {
        Equipment equipment = equipmentService.findById(request.getEquipmentId());
        Size size = sizeService.findById(request.getSizeId());
        ItemStatus status = itemStatusService.getOpenStatus();

        Item item = new Item();
        item.setEquipment(equipment);
        item.setSize(size);
        item.setItemStatus(status);
        item.setLastServiceDate(request.getLastServiceDate());
        item.setPurchasePrice(request.getPurchasePrice());
        item.setDescription(request.getDescription());

        return itemRepository.save(item);
    }

    private void createDefaultPriceForItem(PriceForCreateItemRequest priceFromRequest, Item item) {
        Price price = new Price();
        price.setItem(item);
        price.setPriceValue(priceFromRequest.getPriceValue());
        price.setValidFrom(Instant.now()); // TODO KM there always should be clock in Instant.now() https://youtu.be/PrPQ5xHYa0s
        price.setValidTo(null);
        price.setDescription(priceFromRequest.getDescription());

        priceRepository.save(price);
    }

    @Transactional
    public void update(UpdateItemRequest request) {
        updateItemValidator.validateFields(request);

        Item item = itemRepository.findById(request.getId())
                .orElseThrow(() -> new ItemNotFoundException(request.getId()));
        updateProperFields(item, request);

        itemRepository.save(item);
        updateActualPriceIfNecessary(item, request.getPriceValue());
    }

    private void updateProperFields(Item item, UpdateItemRequest request) {
        if (!request.getSizeId().equals(item.getSize().getId())) {
            Size size = sizeService.findById(request.getSizeId());
            item.setSize(size);
        }

        if (!request.getItemStatusId().equals(item.getItemStatus().getId())) {
            ItemStatus itemStatus = itemStatusService.findById(request.getItemStatusId());
            item.setItemStatus(itemStatus);
        }

        if (!request.getLastServiceDate().equals(item.getLastServiceDate())) {
            item.setLastServiceDate(request.getLastServiceDate());
        }

        if (request.getPurchasePrice().compareTo(item.getPurchasePrice()) != 0) {
            item.setPurchasePrice(request.getPurchasePrice());
        }

        if (!StringUtils.equals(request.getDescription(), item.getDescription())) {
            item.setDescription(request.getDescription());
        }
    }

    private void updateActualPriceIfNecessary(Item item, BigDecimal priceValueFromRequest) {
        Price actualPrice = itemPricesService.findActualPriceForItemByItemId(item.getId());

        if (priceValueFromRequest.compareTo(actualPrice.getPriceValue()) != 0) {
            updateActualPrice(item.getId(), priceValueFromRequest);
        }
    }

    private void updateActualPrice(Integer itemId, BigDecimal priceValueFromRequest) {
        CreatePriceRequest createPriceRequest = new CreatePriceRequest();
        createPriceRequest.setItemId(itemId);
        createPriceRequest.setPriceValue(priceValueFromRequest);

        itemPricesService.createPriceForItem(createPriceRequest);
    }

    public void modifyItemsStatusByIds(List<Integer> itemsIds, ItemStatus statusToModify) {
        List<Item> itemsToModify = itemRepository.findAllByIdIn(itemsIds);

        itemsToModify.forEach(item ->
                item.setItemStatus(statusToModify));

        itemRepository.saveAll(itemsToModify);
    }
}
