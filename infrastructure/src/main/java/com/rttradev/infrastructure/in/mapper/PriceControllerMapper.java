package com.rttradev.infrastructure.in.mapper;

import com.rttradev.application.domain.Price;
import com.rttradev.application.domain.PriceSearchRequest;
import com.rttradev.infrastructure.in.dto.PriceSearchRequestDto;
import com.rttradev.infrastructure.in.dto.PriceSearchResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PriceControllerMapper {

    public static PriceSearchRequest toDomain(PriceSearchRequestDto priceSearchRequestDto) {
        return PriceSearchRequest.builder()
            .productId(priceSearchRequestDto.getProductId())
            .brandId(priceSearchRequestDto.getBrandId())
            .date(priceSearchRequestDto.getDate())
            .build();
    }

    public static PriceSearchResponseDto fromDomain(Price price) {
        return PriceSearchResponseDto.builder()
            .startDate(price.getStartDate())
            .endDate(price.getEndDate())
            .brandName(price.getBrand().getName())
            .productId(price.getProduct().getId())
            .priceList(price.getPriceList())
            .finalPrice(price.getTotalPrice())
            .build();
    }

}
