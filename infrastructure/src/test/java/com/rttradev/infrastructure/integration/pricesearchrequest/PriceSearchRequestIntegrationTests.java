package com.rttradev.infrastructure.integration.pricesearchrequest;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rttradev.infrastructure.config.PopulationTest;
import com.rttradev.infrastructure.in.dto.PriceSearchRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Price search request integration tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(PopulationTest.class)
class PriceSearchRequestIntegrationTests {

    private static final UUID VALID_BRAND_ID = UUID.fromString("90d3f4a9-1fe1-4fb4-b09a-e563e21c4be3");
    private static final UUID VALID_PRODUCT_ID = UUID.fromString("c129db85-37c0-4f47-94d5-3274d8c598fa");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * When search price request is valid then correct price is returned.
     *
     * @param saleDate the sale date
     * @param priceListExpected the price list expected
     * @throws Exception the exception
     */
    @ParameterizedTest
    @CsvSource({
        "2020-06-14T10:00:00, 1",
        "2020-06-14T16:00:00, 2",
        "2020-06-14T21:00:00, 1",
        "2020-06-15T10:00:00, 3",
        "2020-06-16T21:00:00, 4",
    })
    void whenSearchPriceRequestIsValid_thenCorrectPriceIsReturned(LocalDateTime saleDate, Integer priceListExpected) throws Exception {
        PriceSearchRequestDto priceSearchRequestDto = PriceSearchRequestDto.builder()
            .brandId(VALID_BRAND_ID)
            .productId(VALID_PRODUCT_ID)
            .date(saleDate)
            .build();
        mockMvc.perform(post("/price")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(priceSearchRequestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.priceList").value(priceListExpected));
    }

    /**
     * When there are no prices in specified dates then throw price not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void whenThereAreNoPricesInSpecifiedDates_thenThrowPriceNotFoundException() throws Exception {
        PriceSearchRequestDto priceSearchRequestDto = PriceSearchRequestDto.builder()
            .brandId(VALID_BRAND_ID)
            .productId(VALID_PRODUCT_ID)
            .date(LocalDateTime.of(2020, 6, 13, 23, 59, 59))
            .build();
        mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceSearchRequestDto)))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errorDescription").value("There is no available price for specified date, brand and product"));
    }

    /**
     * When price search request has invalid fields then throw detailed exception.
     *
     * @throws Exception the exception
     */
    @Test
    void whenPriceSearchRequestHasInvalidFields_thenThrowDetailedException() throws Exception {
        PriceSearchRequestDto priceSearchRequestDto = PriceSearchRequestDto.builder()
            .date(LocalDateTime.now())
            .brandId(UUID.randomUUID())
            .build();
        mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceSearchRequestDto)))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errorDescription").value("Validation errors"))
            .andExpect(jsonPath("$.jsonErrors").isArray())
            .andExpect(jsonPath("$.jsonErrors").isNotEmpty());
    }

}
