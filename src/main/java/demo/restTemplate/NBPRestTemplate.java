package demo.restTemplate;

import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.dto.ExchangeRate;
import demo.exceptions.NBPApiException;

@Service
public class NBPRestTemplate {

    @Value("${nbp.api.base.url}")
    private String NBP_API_BASE_URL;

    @Value("${nbp.table.type}")
    private String TABLE_TYPE_C;

    @Value("${nbp.currency.code.usd}")
    private String CURRENCY_CODE_USD;

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRate getExchangeRate() {
        try {
            String url = String.format("%s/exchangerates/rates/%s/%s/", NBP_API_BASE_URL, TABLE_TYPE_C, CURRENCY_CODE_USD);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            return restTemplate.getForObject(url, ExchangeRate.class);
        } catch (Exception e) {
            throw new NBPApiException("Błąd podczas pobierania kursu wymiany z API NBP", e);
        }
    }
          
}