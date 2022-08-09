package vn.nodo.tcpaymentpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.nodo.tcpaymentpaymentservice.entity.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findOneByCode(String code);
}
