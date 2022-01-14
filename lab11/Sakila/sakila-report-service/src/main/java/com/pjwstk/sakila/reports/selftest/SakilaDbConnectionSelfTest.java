package com.pjwstk.sakila.reports.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestBase;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SakilaDbConnectionSelfTest extends SelfTestBase {

    public SakilaDbConnectionSelfTest() {
        name = "SakilaDBConnectionSelfTest";
        description = "Checks if establishing connection with sakila database is possible.";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);
        String jdbcUrl = "jdbc:mysql://localhost:3306/sakila";
        String username = "pjwstk";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
            selftestResult.setPassed(true);
        }
        catch (SQLException e) {
            selftestResult.setPassed(false);
            errors.add("An sql exception occurred, this was the stack: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return selftestResult;
    }
}
