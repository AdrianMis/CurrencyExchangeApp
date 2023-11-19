package demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rate {
    private String no;
    private LocalDate  effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
    
    public Rate() {
    }

    
    @JsonCreator
    public Rate(
            @JsonProperty("no") String no,
            @JsonProperty("effectiveDate") LocalDate  effectiveDate,
            @JsonProperty("bid") BigDecimal bid,
            @JsonProperty("ask") BigDecimal ask) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }

    public String getNo() {
        return no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

}