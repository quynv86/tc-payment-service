package vn.nodo.tcpaymentpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.nodo.tcpaymentpaymentservice.entity.PayLog;

public interface PayLogRepository extends JpaRepository<PayLog, Long> {
}
