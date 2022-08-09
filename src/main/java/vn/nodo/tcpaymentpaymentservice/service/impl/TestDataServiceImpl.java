package vn.nodo.tcpaymentpaymentservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nodo.tcpaymentpaymentservice.entity.TestData;
import vn.nodo.tcpaymentpaymentservice.repository.TestDataRepository;
import vn.nodo.tcpaymentpaymentservice.service.TestDataService;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TestDataServiceImpl implements TestDataService {

    private final TestDataRepository testDataRepository;

    public TestDataServiceImpl(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    @Override
    public List<TestData> findAll() {
        return testDataRepository.findAll();
    }
}
