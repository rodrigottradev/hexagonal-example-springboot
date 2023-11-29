package com.rttradev.application.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private UUID id = UUID.randomUUID();
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Product product;
    private Integer priority;
    private Integer priceList;
    private Double totalPrice;
    private String currency;

}
