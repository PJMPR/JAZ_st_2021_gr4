package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SelfTestBase implements SelfTest {
    public String name;
    public String description;

    abstract public SelftestResult execute();
}
