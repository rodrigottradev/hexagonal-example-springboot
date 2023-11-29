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
public class PriceSearchRequest {
    private LocalDateTime date;
    private UUID brandId;
    private UUID productId;

}
