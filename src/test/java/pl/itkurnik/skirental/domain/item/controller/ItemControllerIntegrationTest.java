package pl.itkurnik.skirental.domain.item.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.SizeNotFoundException;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.CalculateItemsRentCostRequest;
import pl.itkurnik.skirental.domain.item.dto.CreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.UpdateItemRequest;
import pl.itkurnik.skirental.domain.item.exception.CalculateItemsRentCostValidationException;
import pl.itkurnik.skirental.domain.item.exception.CreateItemValidationException;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.exception.UpdateItemValidationException;
import pl.itkurnik.skirental.domain.item.service.ItemService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerIntegrationTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private static final String CONTROLLER_URL = "/api/item";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private Item getExampleItem() {
        return Item.builder()
                .id(1)
                .equipment(Equipment.builder()
                        .manufacturer(new Manufacturer())
                        .equipmentCategory(new EquipmentCategory())
                        .build())
                .size(new Size())
                .itemStatus(new ItemStatus())
                .lastServiceDate(LocalDate.parse("2022-12-07"))
                .purchasePrice(new BigDecimal("133.70"))
                .description("Example description")
                .prices(Collections.singleton(Price.builder()
                        .item(new Item())
                        .build()))
                .build();
    }

    @Test
    public void shouldFindAllItems() throws Exception {
        when(itemService.findAll())
                .thenReturn(Collections.singletonList(getExampleItem()));

        mockMvc.perform(get(CONTROLLER_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void shouldFindAllOpen() throws Exception {
        when(itemService.findAllOpen())
                .thenReturn(Collections.singletonList(getExampleItem()));

        mockMvc.perform(get(CONTROLLER_URL + "/open"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void shouldFindById() throws Exception {
        when(itemService.findById(anyInt()))
                .thenReturn(getExampleItem());

        mockMvc.perform(get(CONTROLLER_URL +  "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnNotFoundResponseOnFindById_WhenItemDoesNotExist() throws Exception {
        when(itemService.findById(2))
                .thenThrow(ItemNotFoundException.class);

        mockMvc.perform(get( CONTROLLER_URL + "/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindAllByEquipmentId() throws Exception {
        when(itemService.findAllByEquipmentId(anyInt()))
                .thenReturn(Collections.singletonList(getExampleItem()));

        mockMvc.perform(get( CONTROLLER_URL + "/equipment/2137"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void shouldFindAllBySizeId() throws Exception {
        when(itemService.findAllBySizeId(anyInt()))
                .thenReturn(Collections.singletonList(getExampleItem()));

        mockMvc.perform(get(CONTROLLER_URL +  "/size/1337"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void shouldCreate() throws Exception {
        doNothing().when(itemService)
                .create(isA(CreateItemRequest.class));

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isOk());
    }

    private String getExampleItemInJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

        return writer.writeValueAsString(getExampleItem());
    }

    @Test
    public void shouldReturnBadRequestResponseOnCreate_WhenValidationFailed() throws Exception {
        doThrow(CreateItemValidationException.class)
                .when(itemService).create(any(CreateItemRequest.class));

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestResponseOnCreate_WhenChildObjectNotFound() throws Exception {
        doThrow(EquipmentNotFoundException.class)
                .when(itemService).create(any(CreateItemRequest.class));

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldUpdate() throws Exception {
        doNothing().when(itemService)
                .update(isA(UpdateItemRequest.class));

        mockMvc.perform(put(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestResponseOnUpdate_WhenValidationFailed() throws Exception {
        doThrow(UpdateItemValidationException.class)
                .when(itemService).update(any(UpdateItemRequest.class));

        mockMvc.perform(put(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestResponseOnUpdate_WhenChildObjectNotFound() throws Exception {
        doThrow(SizeNotFoundException.class)
                .when(itemService).update(any(UpdateItemRequest.class));

        mockMvc.perform(put(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestResponseOnUpdate_WhenItemNotFound() throws Exception {
        doThrow(ItemNotFoundException.class)
                .when(itemService).update(any(UpdateItemRequest.class));

        mockMvc.perform(put(CONTROLLER_URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getExampleItemInJson()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDelete() throws Exception {
        doNothing().when(itemService)
                .deleteById(anyInt());

        mockMvc.perform(delete(CONTROLLER_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCalculateItemsRentCost() throws Exception {
        when(itemService.calculateItemsRentCost(any(CalculateItemsRentCostRequest.class)))
                .thenReturn(new BigDecimal("21.37"));

        mockMvc.perform(post(CONTROLLER_URL +  "/items/cost")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("21.37"));
    }

    @Test
    public void shouldReturnBadRequestCodeOnCalculateCost_WhenValidationFailed() throws Exception {
        when(itemService.calculateItemsRentCost(any(CalculateItemsRentCostRequest.class)))
                .thenThrow(CalculateItemsRentCostValidationException.class);

        mockMvc.perform(post(CONTROLLER_URL +  "/items/cost")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldChangeStatusToOpen() throws Exception {
        doNothing().when(itemService)
                .changeStatusToOpen(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/open"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestCodeOnChangeStatusToOpen_WhenItemNotFound() throws Exception {
        doThrow(ItemNotFoundException.class)
                .when(itemService).changeStatusToOpen(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/open"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnConflictCodeOnChangeStatusToOpen_WhenItemIsInBadState() throws Exception {
        doThrow(IllegalStateException.class)
                .when(itemService).changeStatusToOpen(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/open"))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldChangeStatusToBroken() throws Exception {
        doNothing().when(itemService)
                .changeStatusToBroken(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/broken"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestCodeOnChangeStatusToBroken_WhenItemNotFound() throws Exception {
        doThrow(ItemNotFoundException.class)
                .when(itemService).changeStatusToBroken(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/broken"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnConflictCodeOnChangeStatusToBroken_WhenItemIsInBadState() throws Exception {
        doThrow(IllegalStateException.class)
                .when(itemService).changeStatusToBroken(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/broken"))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldChangeStatusToInService() throws Exception {
        doNothing().when(itemService)
                .changeStatusToInService(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/in_service"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestCodeOnChangeStatusToInService_WhenItemNotFound() throws Exception {
        doThrow(ItemNotFoundException.class)
                .when(itemService).changeStatusToInService(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/in_service"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnConflictCodeOnChangeStatusToInService_WhenItemIsInBadState() throws Exception {
        doThrow(IllegalStateException.class)
                .when(itemService).changeStatusToInService(anyInt());

        mockMvc.perform(put(CONTROLLER_URL +  "/1/status/in_service"))
                .andExpect(status().isConflict());
    }
}