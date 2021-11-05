package org.example.queries;

import org.example.Criteria.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);

        Criteria gender = new CriteriaGender();
        Criteria name = new CriteriaName();
        CriteriaSurname surname = new CriteriaSurname();
        Criteria age = new CriteriaAge();
        CriteriaIncome income = new CriteriaIncome();

        if(parameters.getAgeTo()!=0||parameters.getAgeFrom()!=0)result.setItems(age.meetCriteria(parameters));
        if(!parameters.getSelectedGenders().isEmpty())result.setItems(gender.meetCriteria(parameters));
        if(parameters.getIncomeFrom()!=0.0||parameters.getIncomeTo()!=0.0)result.setItems(income.meetCriteria(parameters));
        if(parameters.getName() != null)result.setItems(name.meetCriteria(parameters));
        if(parameters.getSurname() != null)result.setItems(surname.meetCriteria(parameters));

        if (parameters.getPage()!=null) result = Paging.GetMinilist(result,parameters);

        return result;
    }
}
