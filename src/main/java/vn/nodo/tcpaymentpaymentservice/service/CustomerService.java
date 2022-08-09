package vn.nodo.tcpaymentpaymentservice.service;

import vn.nodo.tcpaymentpaymentservice.entity.Customer;

public interface CustomerService {
    Customer getOneOrCreateNew(String code);
}
