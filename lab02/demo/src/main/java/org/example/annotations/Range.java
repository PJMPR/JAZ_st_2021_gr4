package org.example.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    int min() default 0;

    int max() default 10;

    String message() default "number is out of range [0,10]";
}
