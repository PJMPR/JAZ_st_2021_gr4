package org.example.queries.search;

import org.example.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class SearchParameters<TSearchParams> {

    private List<FunctionsParameters> functions = new ArrayList<FunctionsParameters>();
    private Page page;

    private TSearchParams params;

    public TSearchParams getParams() {
        return params;
    }

    public void setParams(TSearchParams params) {
        this.params = params;
    }

    public List<FunctionsParameters> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionsParameters> functions) {
        this.functions = functions;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}

