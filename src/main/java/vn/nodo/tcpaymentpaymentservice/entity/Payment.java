package vn.nodo.tcpaymentpaymentservice.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "trans_ref")
    private String transRef;

    @Column(name = "partner_trans_ref")
    private String partnerTransRef;

    @Column(name = "amount")
    private double amount;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_log_id")
    private PayLog payLog;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
