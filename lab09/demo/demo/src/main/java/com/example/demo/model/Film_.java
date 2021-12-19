package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Film.class)
public abstract class Film_ {

	public static volatile SingularAttribute<Film, BigDecimal> rentalRate;
	public static volatile SingularAttribute<Film, Integer> rentalDuration;
	public static volatile SingularAttribute<Film, Integer> filmId;
	public static volatile SingularAttribute<Film, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Film, BigDecimal> replacementCost;
	public static volatile SingularAttribute<Film, Language> language;
	public static volatile SingularAttribute<Film, String> title;
	public static volatile SingularAttribute<Film, Integer> releaseYear;

	public static final String RENTAL_RATE = "rentalRate";
	public static final String RENTAL_DURATION = "rentalDuration";
	public static final String FILM_ID = "filmId";
	public static final String LAST_UPDATE = "lastUpdate";
	public static final String REPLACEMENT_COST = "replacementCost";
	public static final String LANGUAGE = "language";
	public static final String TITLE = "title";
	public static final String RELEASE_YEAR = "releaseYear";

}

