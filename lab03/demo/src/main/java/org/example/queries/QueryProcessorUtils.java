package org.example.queries;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.criteria.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class QueryProcessorUtils {
    ICriterion criterion = null;

    public List<Person> filterField(String fieldName, Object value, List<Person> personList){
        if(value != null){
            switch (fieldName){
                case "name" -> criterion = new NameCriterion(value.toString());
                case "surname" -> criterion = new SurnameCriterion(value.toString());
                case "ageFrom" -> criterion = new AgeFromCriterion((Integer) value);
                case "ageTo" -> criterion = new AgeToCriterion((Integer) value);
                case "incomeFrom" -> criterion = new IncomeFromCriterion((Double) value);
                case "incomeTo" -> criterion = new IncomeToCriterion((Double) value);
                case "selectedGenders" -> {
                    List<Gender> genderList = (List<Gender>) value;

                    List<Person> multiGenderSearchResult = new ArrayList<>();

                    genderList.forEach(
                            gender -> {
                                GenderCriterion genderCriterion = new GenderCriterion(gender);
                                multiGenderSearchResult.addAll(genderCriterion.meetsCriterion(personList));
                            }
                    );

                    return multiGenderSearchResult;

                }
                default -> criterion = new NameCriterion(null);
            }
        }
        return criterion.meetsCriterion(personList);
    }
}
