package org.example.queries.processingutils;

import org.example.queries.results.Results;
import org.example.queries.search.Page;

public class PageUtils {
    Page page;
    Results result;

    public PageUtils(Page page, Results result) {
        this.page = page;
        this.result = result;
    }

    public void setPageVars(){
        if (page != null) {
            result.setCurrentPage(page.getPageNumber());
            result.setPages(calculatePage());

            int noOfRecordsToSkip = page.getSize() * (result.getCurrentPage() - 1);

            result.setItems(result.getItems()
                    .stream()
                    .skip(noOfRecordsToSkip > result.getItems().size() ? 0 : noOfRecordsToSkip)
                    .limit(page.getSize())
                    .toList());
        }
    }

    private int calculatePage(){
        if(page.getSize() > result.getItems().size()) return 1;
        else return result.getItems().size() / page.getSize();
    }

}
