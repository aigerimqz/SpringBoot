package kz.kbtu.sis2.service;

import kz.kbtu.sis2.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DataService {
    @Autowired
    DataRepository dataRepository;

    private RestTemplate restTemplate = new RestTemplate();
    public Map<String, Object> getExternalData(){
        String api = "https://dummyjson.com/quotes";

        Map<String, Object> response = restTemplate.getForObject(api, Map.class);
        List<Map<String, Object>> quotes =
                (List<Map<String, Object>>) response.get("quotes");

        Map<String, Object> firstQuote = quotes.get(0);
        return Map.of(
                "source", dataRepository.getSource(),
                "quote", firstQuote.get("quote")
        );
    }
}
