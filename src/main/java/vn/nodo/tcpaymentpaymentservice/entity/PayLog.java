package vn.nodo.tcpaymentpaymentservice.entity;


import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.nodo.tcpaymentpaymentservice.controller.request.PayBillRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pay_log")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "json", typeClass = JsonType.class)
@Data
public class PayLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "remote_ip")
    private String remoteIp;

    @Column(name = "partner_trans_ref")
    private String partnerTransRef;

    @Column(name = "content")
    @Type(type = "json")
    private PayBillRequest content;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToOne(mappedBy = "payLog", fetch = FetchType.LAZY)
    private Payment payment;

}
