package vn.nodo.tcpaymentpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.nodo.tcpaymentpaymentservice.entity.TestData;

public interface TestDataRepository extends JpaRepository<TestData, Long> {
}
