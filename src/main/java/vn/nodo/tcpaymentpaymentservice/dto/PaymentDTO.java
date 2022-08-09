package vn.nodo.tcpaymentpaymentservice.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private long id;

    private String transRef;

    private String partnerTransRef;

    private double amount;

    private long customerId;

    private String customerCode;

    private long currencyId;

    private String currencyCode;

    private long partnerId;

    private String partnerName;

    private LocalDateTime createdDate;
}
