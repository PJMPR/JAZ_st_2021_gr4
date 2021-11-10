package org.example.queries.results;

import org.example.model.Person;

public class AgeToFilter extends FilterBase{
    @Override
    protected boolean checkPerson(Person person) {
        return person.getAge()<parameters.getAgeTo();
    }

    @Override
    protected boolean checkParameters() {
        return parameters.getAgeTo()>0;
    }
}
