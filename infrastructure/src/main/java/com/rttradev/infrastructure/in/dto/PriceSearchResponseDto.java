package com.rttradev.infrastructure.in.dto;


import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceSearchResponseDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String brandName;
    private UUID productId;
    private Integer priceList;
    private Double finalPrice;

}
