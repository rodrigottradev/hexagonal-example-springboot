package com.rttradev.application.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to allow Spring Boot to inject beans that are not use cases into the outer layers. Technically there is no difference with
 * UseCase annotation, is just a semantic difference.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DomainComponent {
}
