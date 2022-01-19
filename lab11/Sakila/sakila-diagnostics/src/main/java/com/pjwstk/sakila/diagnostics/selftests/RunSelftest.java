package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RunSelftest {
    List<Selftest> selftestList;

    public static List<SelftestResult> execute(){
        List<SelftestResult> result = new ArrayList<>();
        selftestList.forEach(selftest -> result.add(selftest.execute()));
        return result;
    }
}
