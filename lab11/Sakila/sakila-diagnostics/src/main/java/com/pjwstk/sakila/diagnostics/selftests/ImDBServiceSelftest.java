package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ImDBServiceSelftest extends DefaultSelftest {
    @Value("${imdb.api.key}")String apiKey;
    private final RestTemplate rest = new RestTemplate();

    public ImDBServiceSelftest() {
        name = "ImDBServiceSelfTest";
        description = "Check if connects to imdb api";
    }


    @Override
    public SelftestResult execute() {
        SelftestResult selftestResult = new SelftestResult(name, description, false, null);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-host", "imdb8.p.rapidapi.com");
            headers.add("x-rapidapi-key", "${imdbApiKey}");

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            rest.exchange("https://imdb8.p.rapidapi.com/auto-complete?q=game%20of%20thr", HttpMethod.GET, entity, String.class);
            selftestResult.setPassed(true);
        } catch (HttpClientErrorException e){
            selftestResult.setPassed(false);
            selftestResult.setErrors(List.of(Objects.requireNonNull(e.getMessage())));
        }

        return selftestResult;
    }
}
