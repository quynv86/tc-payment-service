package vn.nodo.tcpaymentpaymentservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.nodo.tcpaymentpaymentservice.entity.TestData;
import vn.nodo.tcpaymentpaymentservice.service.TestDataService;

import java.util.List;

@RestController
public class TestDataController {

    private final TestDataService testDataService;

    public TestDataController(TestDataService testDataService) {
        this.testDataService = testDataService;
    }


    @GetMapping("/api/testData")
    private List<TestData> findAll() {
        return testDataService.findAll();
    }
}
