package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

public interface Selftest {
    SelftestResult execute();
}