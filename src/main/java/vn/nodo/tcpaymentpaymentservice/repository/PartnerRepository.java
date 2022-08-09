package vn.nodo.tcpaymentpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.nodo.tcpaymentpaymentservice.entity.Currency;
import vn.nodo.tcpaymentpaymentservice.entity.Partner;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
