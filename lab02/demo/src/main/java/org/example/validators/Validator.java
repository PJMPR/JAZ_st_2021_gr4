package org.example.validators;
import org.example.annotations.Range;
import org.example.annotations.Regex;
import org.example.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;


public class Validator {
    public void compileMessages(Map<String, List<String>> problems,List<String> namemessages,List<String> numbermessages,List<String> emailmessages){
        if(!namemessages.isEmpty()){
            problems.put("name",namemessages);
        }
        if(!numbermessages.isEmpty()){
            problems.put("number",numbermessages);
        }
        if(!emailmessages.isEmpty()){
            problems.put("email",emailmessages);
        }

    }

    public boolean validatename(String name){
        return name == null || name.equals("");
    }
    public boolean validatenumber(int number, Field field){
        return number>=field.getAnnotation(Range.class).min() && number<=field.getAnnotation(Range.class).max();
    }
    public boolean validateemail(String email,Field field){
        return email.matches(field.getAnnotation(Regex.class).pattern());
    }

    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException, NoSuchFieldException {
        ValidationResult validationresult = new ValidationResult();
        Class clazz = object.getClass();
        Map<String, List<String>> problems = new HashMap<String, List<String>>();
        List<String> namemessages = new ArrayList<>();
        List<String> numbermessages = new ArrayList<>();
        List<String> emailmessages = new ArrayList<>();;
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class) && validatename((String) field.get(object))) {
                validationresult.setValid(false);
                namemessages.add(field.getAnnotation(NotNull.class).message());
                continue;
            }
            if (field.isAnnotationPresent(Regex.class) && !validateemail((String) field.get(object),field)) {
                validationresult.setValid(false);
                emailmessages.add(field.getAnnotation(Regex.class).message());
                continue;
            }
            if (field.isAnnotationPresent(Range.class) && !validatenumber((int) field.get(object),field)) {
                validationresult.setValid(false);
                numbermessages.add(field.getAnnotation(Range.class).message());
            }
        }
        compileMessages(problems,namemessages,numbermessages,emailmessages);

        Field mapa = validationresult.getClass().getDeclaredField("notValidFields");
        mapa.setAccessible(true);
        mapa.set(validationresult,problems);
        validationresult.setValidatedObject(object);
        return validationresult;
    }
}
