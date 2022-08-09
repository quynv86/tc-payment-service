package vn.nodo.tcpaymentpaymentservice.validation.validator;

import vn.nodo.tcpaymentpaymentservice.validation.constraint.AllowedCurrency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AllowedCurrencyValidator implements ConstraintValidator<AllowedCurrency, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
