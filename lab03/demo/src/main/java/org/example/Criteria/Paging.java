package org.example.Criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class Paging {
    public static Results GetMinilist(Results data, SearchParameters para){

        int size = para.getPage().getSize();
        int currentPage = para.getPage().getPageNumber();

        Results result=new Results();

        List<Person> a = new ArrayList<>();
        for (int i = size*(currentPage-1); i < size*(currentPage); i++) {
            System.out.println(data.getItems().get(i).getName());
            a.add(data.getItems().get(i));
        }
        result.setItems(a);
        return result;
    }
}
