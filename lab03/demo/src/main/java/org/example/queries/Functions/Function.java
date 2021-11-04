package org.example.queries.Functions;

import org.example.queries.search.SearchParameters;

import javax.xml.transform.Result;

public interface Function {
    void calculate(Result result, SearchParameters searchParameters);
}
