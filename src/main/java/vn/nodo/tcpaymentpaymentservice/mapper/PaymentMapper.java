package vn.nodo.tcpaymentpaymentservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.nodo.tcpaymentpaymentservice.dto.PaymentDTO;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.code", target = "customerCode")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "currency.code", target = "currencyCode")
    @Mapping(source = "partner.id", target = "partnerId")
    @Mapping(source = "partner.name", target = "partnerName")
    PaymentDTO toDTO(Payment payment);

    List<PaymentDTO> toDTOList(List<Payment> paymentList);
}
