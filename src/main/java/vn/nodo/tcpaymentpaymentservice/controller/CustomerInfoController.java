package vn.nodo.tcpaymentpaymentservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.nodo.tcpaymentpaymentservice.dto.CustomerInfoDTO;

@RestController
public class CustomerInfoController {

    @GetMapping("/getCustomerInfo")
    public CustomerInfoDTO getCustomerInfo() {
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.setCustomerCode("0987654321");
        dto.setFullName("Thomas Edison");
        dto.setPayableAmount(123.45);
        return dto;
    }
}
