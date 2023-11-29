package com.rttradev.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.rttradev.application.config.DomainComponent;
import com.rttradev.application.config.UseCase;

@Configuration
@ComponentScan(
    basePackages = "com.rttradev.application",
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = DomainComponent.class)
    }
)
public class DomainConfig {
}
