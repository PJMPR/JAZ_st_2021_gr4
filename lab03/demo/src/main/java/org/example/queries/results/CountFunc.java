package org.example.queries.results;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class CountFunc {
    public Results countAllRequested(Results results, SearchParameters params){
        List<FunctionsParameters> requests = params.getFunctions();
        List<FunctionResult> counted = results.getFunctionResults();
        for (FunctionsParameters parameters : requests){
            FunctionResult r = new FunctionResult();
            r.setFieldName(parameters.getFieldName());
            r.setFunction(parameters.getFunction());
            if (Objects.equals(parameters.getFunction().toString(), "AVARAGE")){
                r.setValue(AVERAGE(results,parameters));
            }
            else{
                r.setValue(SUM(results,parameters));
            }
            counted.add(r);
        }
        results.setFunctionResults(counted);
        return results;
    }
    public double SUM(Results results,FunctionsParameters param){
        double sum = 0.0;
        String pole = param.getFieldName();
        for (Person person : results.getItems()){
            try {
                Field field = person.getClass().getDeclaredField(pole);
                field.setAccessible(true);
                sum+=field.getDouble((person));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println("wrong field choosen, for such an operation");
                break;
            }
        }
        return sum;
    }
    public double AVERAGE(Results results,FunctionsParameters param){
        return SUM(results,param)/(double)results.getItems().size();
    }

}
