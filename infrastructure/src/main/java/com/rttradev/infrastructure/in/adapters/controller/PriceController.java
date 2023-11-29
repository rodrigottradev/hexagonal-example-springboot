package com.rttradev.infrastructure.in.adapters.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rttradev.application.domain.Price;
import com.rttradev.application.error.PriceNotFoundError;
import com.rttradev.application.ports.in.PriceInputPort;
import com.rttradev.infrastructure.in.dto.PriceSearchRequestDto;
import com.rttradev.infrastructure.in.dto.PriceSearchResponseDto;
import com.rttradev.infrastructure.in.mapper.PriceControllerMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceInputPort priceInputPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceSearchResponseDto searchPrice(@RequestBody @Valid PriceSearchRequestDto priceSearchRequestDto) throws PriceNotFoundError {
        Price price = priceInputPort.searchPrice(PriceControllerMapper.toDomain(priceSearchRequestDto));
        return PriceControllerMapper.fromDomain(price);
    }

}
