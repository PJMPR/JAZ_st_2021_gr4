package com.example.demo.repositories;

import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public List<Film> getFilms(){

        return entityManager.createQuery("SELECT film FROM Film film WHERE film.releaseYear=2006", Film.class)
                .setMaxResults(30)
                .setFirstResult(50)
                .getResultList();

    }
}
