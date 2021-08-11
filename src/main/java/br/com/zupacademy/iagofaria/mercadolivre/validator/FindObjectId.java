package br.com.zupacademy.iagofaria.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = {FindObjectIdValidator.class})
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FindObjectId {

    String message() default "{Validação pega pelo meu validator}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}

