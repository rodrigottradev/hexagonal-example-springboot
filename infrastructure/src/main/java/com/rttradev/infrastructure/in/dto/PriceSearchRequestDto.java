package com.rttradev.infrastructure.in.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceSearchRequestDto {

    @NotNull(message = "Date is mandatory")
    private LocalDateTime date;
    @NotNull(message = "BrandId is mandatory")
    private UUID brandId;
    @NotNull(message = "ProductId is mandatory")
    private UUID productId;

}
