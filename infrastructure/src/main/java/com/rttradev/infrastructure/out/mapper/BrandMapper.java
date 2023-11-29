package com.rttradev.infrastructure.out.mapper;

import com.rttradev.application.domain.Brand;
import com.rttradev.infrastructure.out.entity.BrandEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandMapper {

    public static Brand toDomain(BrandEntity brandEntity) {
        return Brand.builder()
            .id(brandEntity.getId())
            .name(brandEntity.getName())
            .build();
    }

    public static BrandEntity fromDomain(Brand brand) {
        return BrandEntity.builder()
            .id(brand.getId())
            .name(brand.getName())
            .build();
    }

}
