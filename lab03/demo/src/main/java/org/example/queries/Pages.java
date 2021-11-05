package org.example.queries;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.Page;
import java.util.List;
import java.util.stream.Collectors;

public class Pages {
    private Page page;

    public Pages(Page page){
        this.page = page;
    }

    public void prepareDataToDisplay(Results result) {
        if( page == null || page.getSize() > result.getItems().size()) {
            page = new Page(result.getItems().size(), 1);
        }
        result.setPages(calcPageSize(result));
        result.setCurrentPage(page.getPageNumber());
        result.setItems(setDisplaySize(result));
    }

    private List<Person> setDisplaySize(Results results) {
        if(!results.getItems().isEmpty()){
            int Skips = page.getSize() * (page.getPageNumber()-1);

            return results.getItems().stream()
                    .skip(Skips)
                    .limit(page.getSize())
                    .collect(Collectors.toList());
        }
        return results.getItems();
    }

    private int calcPageSize(Results result) {
        return result.getItems().size() / page.getSize();
    }
}