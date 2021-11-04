package org.example.queries.search;

import org.example.queries.results.Results;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;


public class ApplyAllFilters {
    public Results apply(Results results, SearchParameters params) {
        FiltersToApply filters = new FiltersToApply(params,results);
        List<Method> methods = Arrays.stream(filters.getClass().getDeclaredMethods())
                .filter(c -> !c.getName().contains("lambda") && !c.getName().contains("page"))
                .toList();
        for(Method method : methods)
            try {
                method.invoke(filters);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println("ignoring lambda");
            }
        filters.filterpage();
        return results;
    }
}
