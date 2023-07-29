package com.example.recipebook.model;

import org.sqlite.Function;

import java.sql.SQLException;

public class LCase extends Function {
    @Override
    protected void xFunc() {
        try {
            if (args() == 0 || value_text(0) == null) {
                result();
                return;
            }

            String input = value_text(0);
            result(input.toLowerCase());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
