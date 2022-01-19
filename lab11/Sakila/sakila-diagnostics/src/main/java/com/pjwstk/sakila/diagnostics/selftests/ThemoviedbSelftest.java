package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ThemoviedbSelftest extends DefaultSelftest {
    @Value("${themoviesdb.api.key}")String apiKey;
    private final RestTemplate rest = new RestTemplate();

    public ThemoviedbSelftest() {
        name = "ThemoviedbSelftest";
        description = "Check if connects to themoviedb.org api";
    }

    @Override
    public SelftestResult execute() {
        SelftestResult selftestResult = new SelftestResult(name, description, false, null);
        try{
            var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                    2137 +
                    "?api_key=" + apiKey, Object.class).getBody();
            System.out.println(movie);
            selftestResult.setPassed(true);
        } catch (HttpClientErrorException e){
            selftestResult.setPassed(false);
            selftestResult.setErrors(List.of(Objects.requireNonNull(e.getMessage())));
        }

        return selftestResult;
    }


}
