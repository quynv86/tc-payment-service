package vn.nodo.tcpaymentpaymentservice.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nodo.tcpaymentpaymentservice.entity.Customer;
import vn.nodo.tcpaymentpaymentservice.repository.CustomerRepository;
import vn.nodo.tcpaymentpaymentservice.service.CustomerService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getOneOrCreateNew(String code) {
        code = code.trim();
        Optional<Customer> optCustomer = customerRepository.findOneByCode(code);
        if (optCustomer.isPresent()) {
            return optCustomer.get();
        } else {
            return createNew(code);
        }
    }

    @Transactional
    private Customer createNew(String code) {
        Customer cus = new Customer();
        cus.setCode(code);
        customerRepository.save(cus);
        logger.info("New customer created, code = {}", code);
        return cus;
    }
}
