package vn.nodo.tcpaymentpaymentservice.validation.constraint;

import vn.nodo.tcpaymentpaymentservice.validation.validator.AllowedCurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AllowedCurrencyValidator.class)
@Documented
public @interface AllowedCurrency {
    String message() default "{invalidCurrency}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
