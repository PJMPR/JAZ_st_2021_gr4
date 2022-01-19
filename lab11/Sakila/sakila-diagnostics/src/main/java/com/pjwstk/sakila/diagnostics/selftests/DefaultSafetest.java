package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

abstract class DefaultSelftest implements Selftest {
    String name;
    String description;

    abstract public SelftestResult execute();
}
