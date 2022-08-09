package vn.nodo.tcpaymentpaymentservice.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.nodo.tcpaymentpaymentservice.controller.request.SearchPaymentRequest;
import vn.nodo.tcpaymentpaymentservice.controller.response.PaginationDataResponse;
import vn.nodo.tcpaymentpaymentservice.dto.PaymentDTO;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;
import vn.nodo.tcpaymentpaymentservice.entity.Payment_;
import vn.nodo.tcpaymentpaymentservice.mapper.PaymentMapper;
import vn.nodo.tcpaymentpaymentservice.service.PaymentService;

import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/payments")
    public PaginationDataResponse<PaymentDTO> getPayments(SearchPaymentRequest searchRequest, Pageable pageable) {
        Sort sort = pageable.getSort();
        if (sort.isUnsorted()) {
            sort = Sort.by(Sort.Direction.DESC, Payment_.ID);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        Page<Payment> entityPage = paymentService.findPage(searchRequest, pageable);
        List<PaymentDTO> dtoList = paymentMapper.toDTOList(entityPage.getContent());
        Page<PaymentDTO> dtoPage = new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
        return new PaginationDataResponse<>(dtoPage);
    }
}
