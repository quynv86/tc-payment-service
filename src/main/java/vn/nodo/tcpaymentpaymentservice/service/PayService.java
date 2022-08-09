package vn.nodo.tcpaymentpaymentservice.service;

import vn.nodo.tcpaymentpaymentservice.controller.request.PayBillRequest;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;

import javax.servlet.http.HttpServletRequest;

public interface PayService {
    Payment pay(HttpServletRequest httpRequest, PayBillRequest payRequest);
}
