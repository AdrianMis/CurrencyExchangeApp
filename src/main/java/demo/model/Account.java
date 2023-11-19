package demo.model;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal balancePLN;
    private BigDecimal balanceUSD;

    public Account() {
    }

    public Account(Long id, String firstName, String lastName, BigDecimal balancePLN, BigDecimal balanceUSD) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balancePLN = balancePLN;
        this.balanceUSD = balanceUSD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getBalancePLN() {
        return balancePLN;
    }

    public void setBalancePLN(BigDecimal balancePLN) {
        this.balancePLN = balancePLN;
    }

    public BigDecimal getBalanceUSD() {
        return balanceUSD;
    }

    public void setBalanceUSD(BigDecimal balanceUSD) {
        this.balanceUSD = balanceUSD;
    }
}
