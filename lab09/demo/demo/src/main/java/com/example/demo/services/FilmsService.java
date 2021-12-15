package com.example.demo.services;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.FilmQueryParams;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import com.example.demo.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmsService {

    private final FilmsRepository filmsRepository;

    public List<FilmDto> getFilms(FilmQueryParams params){

        //String query = builder.build(params);
        //List<Film> results = filmsRepository.GetResults(query);
        return filmsRepository.getFilms()
                .stream()
                .map(f->new FilmDto(f.getFilmId(),
                        f.getTitle(),
                        f.getReleaseYear(),
                        new LanguageDto(f.getLanguage().getLanguageId(),f.getLanguage().getName()),
                        f.getRentalDuration(), f.getRentalRate(),
                        f.getReplacementCost())).collect(Collectors.toList());
    }
}
