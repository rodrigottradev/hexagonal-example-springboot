package com.rttradev.infrastructure.out.mapper;

import com.rttradev.application.domain.Price;
import com.rttradev.infrastructure.out.entity.PriceEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PriceMapper {

    public static Price toDomain(PriceEntity priceEntity) {
        return Price.builder()
            .id(priceEntity.getId())
            .brand(BrandMapper.toDomain(priceEntity.getBrandEntity()))
            .product(ProductMapper.toDomain(priceEntity.getProductEntity()))
            .startDate(priceEntity.getStartDate())
            .endDate(priceEntity.getEndDate())
            .currency(priceEntity.getCurrency())
            .priority(priceEntity.getPriority())
            .totalPrice(priceEntity.getPrice())
            .priceList(priceEntity.getPriceList())
            .build();
    }

}
