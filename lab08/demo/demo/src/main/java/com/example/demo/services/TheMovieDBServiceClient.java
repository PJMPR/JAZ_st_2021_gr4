package com.example.demo.services;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.MoviePagesDto;
import com.example.demo.quartz.payload.StatusInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TheMovieDBServiceClient {

    private final RestTemplate rest;
    @Value("${themoviesdb.api.key}")String apiKey;

    @Cacheable(value = "TheMovieDb", key ="#id")
    public MovieDto getMovie(int id){
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + apiKey, MovieDto.class).getBody();
        return movie;
    }

    public List<MovieDto> getMoviesByYear(int year){
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&primary_release_year=" + year;
        var movieDtos  =
                rest.getForEntity(
                        url,
                        MoviePagesDto.class).getBody();
        StatusInfo.getInstance().setMoviesToUpdate(movieDtos.getTotal_results());
        return movieDtos.getResults();
    }



    public void reloadData(int year) {
        StatusInfo status = StatusInfo.getInstance();
        List<MovieDto> movieDtos = getMoviesByYear(year);

        for (int fromYear = year; fromYear <= Calendar.getInstance().get(Calendar.YEAR); fromYear++) {
            movieDtos.forEach(movie ->getMovie(movie.getId()));
            for (int i = 0; i < status.getMoviesToUpdate(); i++) {
                var moveDbDTO = getMovie(i);
                log.info(moveDbDTO.toString());
            }

        }
    }

    public Object getSystemStatusInfo() {
        return StatusInfo.getInstance();
    }
}
