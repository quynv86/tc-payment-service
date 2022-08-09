package vn.nodo.tcpaymentpaymentservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.nodo.tcpaymentpaymentservice.controller.request.PayBillRequest;
import vn.nodo.tcpaymentpaymentservice.controller.response.PayResponse;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;
import vn.nodo.tcpaymentpaymentservice.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }


    @PostMapping("/payBill")
    public PayResponse payBill(@Valid @RequestBody PayBillRequest payBillRequest, HttpServletRequest httpRequest) {
        logger.info("Processing payment request from " + httpRequest.getRemoteAddr());
        Payment payment = payService.pay(httpRequest, payBillRequest);

        PayResponse resp = new PayResponse();
        resp.setTransRef(payment.getTransRef());
        return resp;
    }
}
