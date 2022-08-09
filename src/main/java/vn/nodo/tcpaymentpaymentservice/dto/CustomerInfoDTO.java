package vn.nodo.tcpaymentpaymentservice.dto;

import lombok.Data;

@Data
public class CustomerInfoDTO {
    private String customerCode;
    private String fullName;
    private double payableAmount;
}
