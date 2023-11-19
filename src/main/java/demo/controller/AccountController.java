package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.AccountCreationRequest;
import demo.dto.ExchangeRequest;
import demo.model.Account;
import demo.restTemplate.NBPRestTemplate;
import demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
	private final NBPRestTemplate nbpRestTemplate;
    private final AccountService accountService;
	
    @Autowired
    public AccountController(AccountService accountService, NBPRestTemplate nbpRestTemplate) {
        this.accountService = accountService;
        this.nbpRestTemplate = nbpRestTemplate;
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountId) {
    	Account response = accountService.getAccountInfo(accountId);

    	return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationRequest request) {
    	Account response = accountService.createAccount(request);
    	return ResponseEntity.ok(response);
    }

    @PostMapping("/exchange")
    public ResponseEntity<Account> exchangeCurrency(@RequestBody ExchangeRequest request) {

    	Account result = accountService.exchange(request);
    	return ResponseEntity.ok(result);
    }


}