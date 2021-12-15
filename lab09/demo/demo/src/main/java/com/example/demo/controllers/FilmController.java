package com.example.demo.controllers;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.FilmQueryParams;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.services.FilmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmController {
   private final FilmsService filmsService;
    private static final List<FilmDto> films = List.of(
            new FilmDto(1, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(2, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(3, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(4, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(5, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(6, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(7, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20)),
            new FilmDto(8, "tytul", 2000, new LanguageDto(1, "polish"), 2, new BigDecimal(2.99), new BigDecimal(20))

    );

    @GetMapping
    public ResponseEntity getFilms(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "language",required = false) Integer language,
            @RequestParam(value = "release_year",required = false) Integer releaseYear

    ){
        return ResponseEntity.ok(filmsService.getFilms(new FilmQueryParams()));
    }

}
