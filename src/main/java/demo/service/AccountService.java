package demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dto.AccountCreationRequest;
import demo.dto.ExchangeRate;
import demo.dto.ExchangeRequest;
import demo.exceptions.InsufficientFundsException;
import demo.exceptions.UnsupportedCurrencyException;
import demo.model.Account;
import demo.dto.CurrencyTypeEnum;
import demo.repository.AccountRepository;
import demo.restTemplate.NBPRestTemplate;

@Service
public class AccountService {
	    private NBPRestTemplate nbpRestTemplate;
	    private AccountRepository accountRepository;

	    public AccountService(NBPRestTemplate nbpRestTemplate, AccountRepository accountRepository) {
	    	this.accountRepository = accountRepository;
	        this.nbpRestTemplate = nbpRestTemplate;
	    }
	    
	    public Account getAccountInfo(Long accountId) {
	    	accountRepository.getAccountById(accountId);
	    	
	        return accountRepository.getAccountById(accountId);
	    }

	    public Account createAccount(AccountCreationRequest request) {
	        Account account = new Account(null, request.getFirstName(), request.getLastName(), request.getInitialBalancePLN(), BigDecimal.ZERO);
	    	return accountRepository.saveAccount(account);
	    }
	    
	    @Transactional
	    public Account exchange(ExchangeRequest request) {
	    	
	    	Account account = this.getAccountInfo(request.getAccountId());
	    	BigDecimal amountToExchange = request.getAmount();
	    	
	    	if(request.getCurrencyTo().equals(CurrencyTypeEnum.USD)) 
	    		return this.exchangePlnToUsd(account,amountToExchange);
	    	
	    	if(request.getCurrencyTo().equals(CurrencyTypeEnum.PLN)) 
	    		return this.exchangeUsdToPln(account,amountToExchange);
	    	
	    	throw new UnsupportedCurrencyException("Nie znaleziono takiego typu jak " + request.getCurrencyTo());
	    }
	    
	    private Account exchangePlnToUsd(Account account, BigDecimal amountToExchange) {
	    	BigDecimal plnBalance = account.getBalancePLN();
	    	BigDecimal usdBalance = account.getBalanceUSD();
	    	if(plnBalance.compareTo(amountToExchange) < 0) {
	    		 throw new InsufficientFundsException("Niewystarczające środki");
	    	}
	    	
	    	BigDecimal rate = this.getExchangeToUSDRate();
	    	BigDecimal exchangedMoney = amountToExchange.multiply(rate);
	    	
	    	
	    	BigDecimal newUsdBalance = usdBalance.add(exchangedMoney);
	    	
	    	BigDecimal newPlnBalance = plnBalance.subtract(amountToExchange);
	    	
	    	
	    
	    	return accountRepository.updateAccountBalance(account.getId(),newUsdBalance,newPlnBalance);
	    }
	    
	    private Account exchangeUsdToPln(Account account, BigDecimal amountToExchange) {
	    	BigDecimal plnBalance = account.getBalancePLN();
	    	BigDecimal usdBalance = account.getBalanceUSD();
	    	if(usdBalance.compareTo(amountToExchange) < 0) {
	    		 throw new InsufficientFundsException("Niewystarczające środki");
	    	}
	    	
	    	BigDecimal rate = this.getExchangeToPLNRate();
	    	BigDecimal exchangedMoney = amountToExchange.multiply(rate);
	    	
	    	
	    	BigDecimal newUsdBalance = usdBalance.subtract(amountToExchange);
	    	
	    	BigDecimal newPlnBalance = plnBalance.add(exchangedMoney);
	    	
	    	
	    
	    	return accountRepository.updateAccountBalance(account.getId(),newUsdBalance,newPlnBalance);
	    }

	    private BigDecimal getExchangeToUSDRate() {
	    	ExchangeRate exchangeRate = nbpRestTemplate.getExchangeRate();
	    	BigDecimal sellUSDRate= exchangeRate.getCurrentCurrency().getAsk();
	    	return BigDecimal.ONE.divide(sellUSDRate,6, RoundingMode.HALF_UP);
	    }
	    
	    private BigDecimal getExchangeToPLNRate() {
	    	ExchangeRate exchangeRate = nbpRestTemplate.getExchangeRate();
	    	BigDecimal buyUSDRate= exchangeRate.getCurrentCurrency().getBid();
	        return buyUSDRate;  
	    }

	}