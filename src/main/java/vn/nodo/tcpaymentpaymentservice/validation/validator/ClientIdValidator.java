package vn.nodo.tcpaymentpaymentservice.validation.validator;

import vn.nodo.tcpaymentpaymentservice.validation.constraint.ClientId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientIdValidator implements ConstraintValidator<ClientId, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return true;
    }
}
