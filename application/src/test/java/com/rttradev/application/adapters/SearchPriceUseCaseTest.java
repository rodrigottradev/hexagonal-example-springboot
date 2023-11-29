package com.rttradev.application.adapters;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rttradev.application.domain.Brand;
import com.rttradev.application.domain.Price;
import com.rttradev.application.domain.PriceSearchRequest;
import com.rttradev.application.domain.Product;
import com.rttradev.application.error.PriceNotFoundError;
import com.rttradev.application.ports.out.PriceOutputPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPriceUseCaseTest {

    private static final UUID BRAND_ID = UUID.fromString("90d3f4a9-1fe1-4fb4-b09a-e563e21c4be3");
    private static final UUID PRODUCT_ID = UUID.fromString("c129db85-37c0-4f47-94d5-3274d8c598fa");
    private static final UUID PRICE_ID = UUID.fromString("13b0869a-765a-11ee-b962-0242ac120002");

    @Mock
    private PriceOutputPort priceOutputPort;
    @InjectMocks
    private SearchPriceUseCase searchPriceUseCase;

    @Test
    void whenValidPriceSearchRequest_thenReturnPrice() throws PriceNotFoundError {
        LocalDateTime date = LocalDateTime.now();
        PriceSearchRequest priceSearchRequest = PriceSearchRequest.builder()
            .date(date)
            .productId(PRODUCT_ID)
            .brandId(BRAND_ID)
            .build();
        Product product = new Product(PRODUCT_ID);
        Brand brand = new Brand(BRAND_ID, "Brand Name");

        Price price = Price.builder()
            .id(PRICE_ID)
            .startDate(date)
            .endDate(date)
            .product(product)
            .brand(brand)
            .priceList(1)
            .totalPrice(1d)
            .build();
        when(priceOutputPort.findHighestPriorityPriceByDateAndProductAndBrand(date, PRODUCT_ID, BRAND_ID)).thenReturn(
            Optional.of(price));
        assertEquals(price, searchPriceUseCase.searchPrice(priceSearchRequest));
    }

    @Test
    void whenPriceDoesNotExist_thenThrowPriceNotFoundException() {
        assertThrows(PriceNotFoundError.class, () -> searchPriceUseCase.searchPrice(new PriceSearchRequest()));
    }
}
