package org.example.queries.Criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;

public class CriteriaPage implements Filter{
    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if(searchParameters.getPage()!=null){

            int pageSize = searchParameters.getPage().getSize();
            int pageNumber = searchParameters.getPage().getPageNumber();
            int next = 0;
            ArrayList<Person> people = new ArrayList<>();

            results.setCurrentPage(pageNumber);
            results.setPages((results.getItems().size()/pageSize));

            if(pageNumber>1)
                next = (pageSize*(pageNumber-1));

            for(int i = 0; i < pageSize;i++){
                people.add(results.getItems().get(next++));
                if(next == results.getItems().size())
                    break;
            }
            results.setItems(people);
        }

    }
}
