package vn.nodo.tcpaymentpaymentservice.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import vn.nodo.tcpaymentpaymentservice.validation.constraint.AllowedCurrency;
import vn.nodo.tcpaymentpaymentservice.validation.constraint.ClientId;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PayBillRequest {
    @NotBlank(message = "customerCode must not be empty")
    private String customerCode;

    private long ts; // Timestamp

    @DecimalMin(value = "0.000001", message = "'amount' must be greater than zero")
    private double amount;

    @AllowedCurrency
    private String currency;

    @NotBlank(message = "transRef must not be null")
    private String transRef;

    @ClientId
    private Long clientId;

    @NotBlank(message = "Signature (sig) must not be empty")
    private String sig; // Signature
}
