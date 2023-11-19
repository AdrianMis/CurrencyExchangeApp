package demo.dto;

import org.springframework.lang.NonNull;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class ExchangeRequest {
    @NotNull(message = "accountId cannot be null")
    private Long accountId;

    @NotNull(message = "amount cannot be null")
    private BigDecimal amount;

    @NotNull(message = "currencyTo cannot be null")
    private CurrencyTypeEnum currencyTo;


    public ExchangeRequest() {
    }

    public ExchangeRequest(Long accountId, BigDecimal amount, CurrencyTypeEnum currencyTo) {
        this.accountId = accountId;
        this.amount = amount;
        this.currencyTo = currencyTo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyTypeEnum getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(CurrencyTypeEnum currencyTo) {
        this.currencyTo = currencyTo;
    }
}
