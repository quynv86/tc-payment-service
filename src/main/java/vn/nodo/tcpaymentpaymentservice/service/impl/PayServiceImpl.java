package vn.nodo.tcpaymentpaymentservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vn.nodo.tcpaymentpaymentservice.controller.request.PayBillRequest;
import vn.nodo.tcpaymentpaymentservice.entity.Currency;
import vn.nodo.tcpaymentpaymentservice.entity.Partner;
import vn.nodo.tcpaymentpaymentservice.entity.PayLog;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;
import vn.nodo.tcpaymentpaymentservice.repository.CurrencyRepository;
import vn.nodo.tcpaymentpaymentservice.repository.PartnerRepository;
import vn.nodo.tcpaymentpaymentservice.repository.PayLogRepository;
import vn.nodo.tcpaymentpaymentservice.repository.PaymentRepository;
import vn.nodo.tcpaymentpaymentservice.service.CustomerService;
import vn.nodo.tcpaymentpaymentservice.service.PayService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayServiceImpl implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    private final PayLogRepository payLogRepository;
    private final PaymentRepository paymentRepository;
    private final CurrencyRepository currencyRepository;
    private final PartnerRepository partnerRepository;
    private final CustomerService customerService;

    public PayServiceImpl(PayLogRepository payLogRepository, PaymentRepository paymentRepository, CurrencyRepository currencyRepository, PartnerRepository partnerRepository, CustomerService customerService) {
        this.payLogRepository = payLogRepository;
        this.paymentRepository = paymentRepository;
        this.currencyRepository = currencyRepository;
        this.partnerRepository = partnerRepository;
        this.customerService = customerService;
    }

    @Override
    public Payment pay(HttpServletRequest httpRequest, PayBillRequest payRequest) {
        PayLog payLog = recordPayLog(httpRequest, payRequest);
        logger.info("Pay log created, ID = " + payLog.getId());
        Payment payment = savePayment(payLog, payRequest);
        logger.info("New payment created: ID = {}", payment.getId());
        return payment;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private Payment savePayment(PayLog payLog, PayBillRequest payRequest) {
        Payment pm = mapPayment(payRequest);
        pm.setPayLog(payLog);
        pm.setTransRef(UUID.randomUUID().toString().replaceAll("-", ""));
        paymentRepository.save(pm);
        return pm;
    }

    private Payment mapPayment(PayBillRequest request) {
        Payment pm = new Payment();
        pm.setPartnerTransRef(request.getTransRef());
        pm.setAmount(request.getAmount());

        String currencyCode = request.getCurrency();
        Optional<Currency> optCurrency = currencyRepository.findOneByCode(currencyCode);
        if (optCurrency.isPresent()) {
            pm.setCurrency(optCurrency.get());
        } else {
            // It was validated
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad currency code: " + currencyCode);
        }

        long clientId = request.getClientId();
        Optional<Partner> optPartner = partnerRepository.findById(clientId);
        if (optPartner.isPresent()) {
            pm.setPartner(optPartner.get());
        } else {
            // It was validated
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad partner ID (clientID): " + clientId);
        }

        String customerCode = request.getCustomerCode();
        pm.setCustomer(customerService.getOneOrCreateNew(customerCode));

        return pm;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private PayLog recordPayLog(HttpServletRequest httpRequest, PayBillRequest payRequest) {
        PayLog payLog = new PayLog();
        payLog.setPartnerTransRef(payRequest.getTransRef());
        payLog.setRemoteIp(httpRequest.getRemoteAddr());
        payLog.setContent(payRequest);
        payLogRepository.save(payLog);
        return payLog;
    }
}
