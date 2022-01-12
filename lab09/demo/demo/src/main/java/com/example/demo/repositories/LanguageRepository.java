package com.example.demo.repositories;

import com.example.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    //@Query(value = "SELECT * FROM language WHERE name = :name", nativeQuery = true)
    @Query(value = "SELECT language FROM Language language " +
            "WHERE language.name=:name AND language.languageId>0")
    public List<Language> findAllByName(String name);
}
