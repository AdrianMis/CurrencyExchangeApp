package demo.dto;

import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRate {
    private String table;  
    private String currency;
    private String code;
    private List<Rate> rates;
    
    public ExchangeRate() {
    }
    
    @JsonCreator
    public ExchangeRate(
            @JsonProperty("table") String table,
            @JsonProperty("currency") String currency,
            @JsonProperty("code") String code,
            @JsonProperty("rates") List<Rate> rates) {
    	System.out.print("constructor");
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }
    
	public Rate getCurrentCurrency() {
	        if (rates == null || rates.isEmpty()) {
	            return null; 
	        }

	        rates.sort(Comparator.comparing(Rate::getEffectiveDate));

	        return rates.get(0);
	    
	}  

}

