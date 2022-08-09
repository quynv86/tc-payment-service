package vn.nodo.tcpaymentpaymentservice.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nodo.tcpaymentpaymentservice.controller.request.SearchPaymentRequest;
import vn.nodo.tcpaymentpaymentservice.entity.Payment;
import vn.nodo.tcpaymentpaymentservice.entity.Payment_;
import vn.nodo.tcpaymentpaymentservice.repository.PaymentRepository;
import vn.nodo.tcpaymentpaymentservice.service.PaymentService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Page<Payment> findPage(SearchPaymentRequest search, Pageable pageable) {
        return paymentRepository.findAll(new PaymentSpecification(search), pageable);
    }

    public static class PaymentSpecification implements Specification<Payment> {
        private final SearchPaymentRequest search;

        public PaymentSpecification(SearchPaymentRequest search) {
            this.search = search;
        }

        @Override
        public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (search == null) return null;
            List<Predicate> predicateList = new ArrayList<>();
            LocalDateTime fromDate = search.getFromDateValue();
            if (fromDate != null) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get(Payment_.CREATED_DATE), fromDate));
            }

            LocalDateTime toDate = search.getToDateValue();
            if (toDate != null) {
                predicateList.add(cb.lessThanOrEqualTo(root.get(Payment_.CREATED_DATE), toDate));
            }
            Predicate[] predicateArray = predicateList.toArray(new Predicate[0]);
            return cb.and(predicateArray);
        }
    }
}
