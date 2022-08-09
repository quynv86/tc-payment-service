package vn.nodo.tcpaymentpaymentservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.nodo.tcpaymentpaymentservice.controller.request.SearchPaymentRequest;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;

public interface PaymentService {
    Page<Payment> findPage(SearchPaymentRequest search, Pageable pageable);
}
