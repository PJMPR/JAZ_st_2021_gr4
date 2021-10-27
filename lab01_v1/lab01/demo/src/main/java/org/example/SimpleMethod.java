package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SimpleMethod {

    Method method;
    public SimpleMethod(Method m) {
        method =m;
    }

    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    public boolean startsWith(String prefix) {
        return method.getName().startsWith(prefix);
    }

    public boolean isVoid() {
        return method.getReturnType().equals(void.class);
    }

    public boolean hasParamsCount(int n) {
        return method.getParameterCount()==n;
    }

    public boolean isGetter() {
        return isPublic()
                && !isVoid()
                && hasParamsCount(0)
                && (startsWith("get")||startsWith("is"));

    }

    public boolean isSetter(){
        return isPublic()
                &&isVoid()
                &&hasParamsCount(1)
                &&startsWith("set");
    }

    public String getFieldName(){
        if(startsWith("get") || startsWith("set"))
            return method.getName().substring(3);
        if(startsWith("is"))
            return method.getName().substring(2);
        return "";
    }
}
