package demo.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;


public class AccountCreationRequest {
	@NotNull
    private String firstName;
	@NotNull
    private String lastName;
	@NotNull
    private BigDecimal initialBalancePLN;

    public AccountCreationRequest() {
    }

    public AccountCreationRequest(String firstName, String lastName, BigDecimal initialBalancePLN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.initialBalancePLN = initialBalancePLN;
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

    public BigDecimal getInitialBalancePLN() {
        return initialBalancePLN;
    }

    public void setInitialBalancePLN(BigDecimal initialBalancePLN) {
        this.initialBalancePLN = initialBalancePLN;
    }
}
