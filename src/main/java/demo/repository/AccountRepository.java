package demo.repository;

import org.springframework.stereotype.Repository;

import demo.exceptions.AccountNotFoundException;
import demo.model.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {

    private final Map<Long, Account> accountMap = new HashMap<>();
    private Long idGenerator = 1L;

    public Account saveAccount(Account account) {
        Long accountId = generateAccountId();
        account.setId(accountId);
        accountMap.put(accountId, account);
        return account;
    }
    
    public Account updateAccountBalance(Long id, BigDecimal newUSDBalance, BigDecimal newPLNBalance) {
    	Account account = this.getAccountById(id);
    	account.setBalanceUSD(newUSDBalance);
    	account.setBalancePLN(newPLNBalance);
    	
    	accountMap.put(id, account);
    	return account;
    }
   
    public Account getAccountById(Long accountId) {
    	Account account = accountMap.get(accountId);
        if (account == null) {
        	throw new AccountNotFoundException("Nie znaleziono konta o ID: " + accountId);
        }
        return account;
    }

    private Long generateAccountId() {
        return idGenerator++;
    }
}