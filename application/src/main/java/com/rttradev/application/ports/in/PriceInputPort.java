package com.rttradev.application.ports.in;


import com.rttradev.application.domain.Price;
import com.rttradev.application.domain.PriceSearchRequest;
import com.rttradev.application.error.PriceNotFoundError;

public interface PriceInputPort {

    Price searchPrice(PriceSearchRequest priceSearchRequest) throws PriceNotFoundError;

}
