package com.example.demo.controllers;
import java.util.Calendar;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.contract.MovieDto;
import com.example.demo.FilmService;
import com.example.demo.contract.MovieDTO2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("movies-client")
public class MoviesController {

    FilmService filmService;
    @Value("${themovies.api.key}")
    String apiKey;

    public MoviesController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getData(@PathVariable("id") int id){
        return ResponseEntity.ok(filmService.getMoveInfo(id));
    }

    @GetMapping
    @RequestMapping("/imdb/{id}")
    public ResponseEntity<MovieDTO2> getDataIMDB(@PathVariable("id") String id){
        return ResponseEntity.ok(filmService.getMovieInfoFromIMDB(id));
    }

    @PostMapping("/updater/status")
    public ResponseEntity getSystemStatus(){
        return ResponseEntity.ok(filmService.getSystemStatusInfo());
    }

    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(filmService.reloadData());
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year) {
        return ResponseEntity.ok(filmService.reloadDataByYear(year));
    }

}
