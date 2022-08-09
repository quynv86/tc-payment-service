package vn.nodo.tcpaymentpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.nodo.tcpaymentpaymentservice.entity.Currency;
import vn.nodo.tcpaymentpaymentservice.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findOneByCode(String code);
}
