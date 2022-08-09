package vn.nodo.tcpaymentpaymentservice.controller.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPaymentRequest {
    @JsonProperty
    private String fromDate;

    @JsonProperty
    private String toDate;

    public LocalDateTime getFromDateValue() {
        if (fromDate == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        try {
            return LocalDateTime.from(formatter.parse(fromDate));
        } catch (Exception e) {
            // skip
        }
        return null;
    }

    public LocalDateTime getToDateValue() {
        if (toDate == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        try {
            return LocalDateTime.from(formatter.parse(toDate));
        } catch (Exception e) {
            // skip
        }
        return null;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
