package com.pjwstk.sakila.diagnostics.selftests;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SakilaConnectionSelftest extends DefaultSelftest {
    public SakilaConnectionSelftest() {
        name = "SakilaConnectionSelftest";
        description = "Check if connects to database";
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
            errors.add("SQL error: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return selftestResult;
    }
}

