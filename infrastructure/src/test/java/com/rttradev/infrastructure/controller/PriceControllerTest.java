package com.rttradev.infrastructure.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rttradev.application.domain.Brand;
import com.rttradev.application.domain.Price;
import com.rttradev.application.domain.PriceSearchRequest;
import com.rttradev.application.domain.Product;
import com.rttradev.application.error.PriceNotFoundError;
import com.rttradev.application.ports.in.PriceInputPort;
import com.rttradev.infrastructure.in.adapters.controller.PriceController;
import com.rttradev.infrastructure.in.dto.PriceSearchRequestDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Price controller test.
 */
@WebMvcTest(PriceController.class)
class PriceControllerTest {

    private static final UUID BRAND_ID = UUID.fromString("90d3f4a9-1fe1-4fb4-b09a-e563e21c4be3");
    private static final UUID PRODUCT_ID = UUID.fromString("c129db85-37c0-4f47-94d5-3274d8c598fa");
    private static final UUID PRICE_ID = UUID.fromString("13b0869a-765a-11ee-b962-0242ac120002");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PriceInputPort priceInputPort;

    /**
     * When search request with valid data then price is returned.
     *
     * @throws Exception the exception
     */
    @Test
    void whenSearchRequestWithValidData_thenPriceIsReturned() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Integer priceListExpected = 1;
        Product product = new Product(PRODUCT_ID);
        Brand brand = new Brand(BRAND_ID, "Brand Name");
        Price price = Price.builder()
            .id(PRICE_ID)
            .startDate(date)
            .endDate(date)
            .product(product)
            .brand(brand)
            .priceList(priceListExpected)
            .totalPrice(1d)
            .build();
        PriceSearchRequestDto priceSearchRequestDto = PriceSearchRequestDto.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .date(date)
            .build();

        PriceSearchRequest priceSearchRequest = PriceSearchRequest.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .date(date)
            .build();

        when(priceInputPort.searchPrice(priceSearchRequest)).thenReturn(price);

        mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceSearchRequestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.priceList").value(priceListExpected));
    }

    /**
     * When search price with non existent data then throws not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void whenSearchPriceWithNonExistentData_thenThrowsNotFoundException() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        PriceSearchRequestDto priceSearchRequestDto = PriceSearchRequestDto.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .date(date)
            .build();
        PriceSearchRequest priceSearchRequest = PriceSearchRequest.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .date(date)
            .build();
        when(priceInputPort.searchPrice(priceSearchRequest)).thenThrow(new PriceNotFoundError("Error description", 404));

        mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceSearchRequestDto)))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errorDescription").value("Error description"));

    }

}
