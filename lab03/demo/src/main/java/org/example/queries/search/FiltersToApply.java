package org.example.queries.search;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;



public class FiltersToApply {
    private final Results results;
    private final SearchParameters param;

    public FiltersToApply(SearchParameters param, Results results) {
        this.results=results;
        this.param=param;
    }
    public void filterGenders(){
        if(!param.getSelectedGenders().isEmpty()) {
            results.setItems(results.getItems().stream()
                    .filter(s -> param.getSelectedGenders().contains(s.getGender()))
                    .toList());
        }
     }

    public void IncomeFrom(){
        if(param.getIncomeFrom()!=0.0) {
            double income = param.getIncomeFrom();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getIncome() >= income)
                    .toList());
        }
    }

    public void IncomeTo(){
        if(param.getIncomeTo()!=0.0) {
            System.out.println(param.getIncomeTo());
            double income =param.getIncomeTo();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getIncome() <= income)
                    .toList());
            System.out.println(results.getItems());
        }
    }

    public void rangeAgefrom(){
        if(param.getAgeFrom()!=0.0) {
            int age = param.getAgeFrom();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getAge() >= age)
                    .toList());
        }
    }
    public void rangeAgeTo(){
        if(param.getAgeTo()!=0.0) {
            int age = param.getAgeTo();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getAge() <= age)
                    .toList());
        }
    }

    public void filtersurname(){
        if(param.getSurname()!=null) {
            String surname = param.getSurname().toLowerCase();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getSurname().equals(surname))
                    .toList());
        }
    }

    public void filtername(){
        if(param.getName()!=null) {
            String name = param.getName().toLowerCase();
            results.setItems(results.getItems().stream()
                    .filter(s -> s.getName().equals(name))
                    .toList());
            }
        }

    public void filterpage(){
        if(param.getPage()!=null) {
            Page page = param.getPage();
            results.setCurrentPage(page.getPageNumber());
            results.setItems(results.getItems().stream()
                    .filter(s -> results.getItems().indexOf(s) < page.getSize() * page.getPageNumber() && results.getItems().indexOf(s) >= page.getSize() * (page.getPageNumber() - 1))
                    .toList());
            results.setPages((int) Math.round(Math.ceil((double) results.getItems().size() / param.getPage().getSize())));
        }
    }

}


