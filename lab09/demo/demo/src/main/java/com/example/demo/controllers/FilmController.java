package com.example.demo.controllers;

import com.example.demo.contracts.FilmDto;
import com.example.demo.model.Film;
import com.example.demo.model.Film_;
import com.example.demo.services.FilmsService;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmController {
   private final FilmsService filmsService;

    @GetMapping
    public ResponseEntity getFilms(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "language",required = false) String language,
            @RequestParam(value = "release_year",required = false) Integer releaseYear,
            @RequestParam(value = "rental_duration",required = false) Integer rentalDuration,
            @RequestParam(value = "rental_rate",required = false) BigDecimal rentalRate,
            @RequestParam(value = "replacement_costs",required = false) BigDecimal replacementCosts

    ){
        Map<SingularAttribute<Film, ?>, Object> params = new HashMap<>();

        if(title != null) params.put(Film_.title, title);
        if(language != null) params.put(Film_.language, language);
        if(releaseYear != null) params.put(Film_.releaseYear, releaseYear);
        if(rentalDuration != null) params.put(Film_.rentalDuration, rentalDuration);
        if(rentalRate != null) params.put(Film_.rentalRate, rentalRate);
        if(replacementCosts != null) params.put(Film_.replacementCost, replacementCosts);

        return ResponseEntity.ok(filmsService.getFilmsFromDB(params));
    }

    @PostMapping
    public ResponseEntity<FilmDto> addFilm(@RequestBody FilmDto filmDto){
        filmsService.putFilmToRepository(filmDto);
        return new ResponseEntity<>(filmDto, HttpStatus.OK);
    }



}
