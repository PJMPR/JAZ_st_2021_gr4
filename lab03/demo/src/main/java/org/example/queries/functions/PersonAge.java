package org.example.queries.functions;

import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class PersonAge extends CalculateField{
    public PersonAge(List<Person> itemsComputed){
        super("income", itemsComputed.stream()
                .collect(Collectors.summarizingDouble(Person::getAge)));
    }
}