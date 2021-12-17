package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.model.Film;
import com.example.demo.model.Film_;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    public List<Film> getFilmsFromDB(Map<SingularAttribute<Film, ?>, Object> params){
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> query = qb.createQuery(Film.class);
        Root<Film> root = query.from(Film.class);
        query.select(root);

        params.forEach((field, value) -> query.where(qb.equal(root.get(field), value)));

        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public void insertFilmToDB(FilmDto filmDto) {
        this.entityManager.persist(filmDto);
    }

}
