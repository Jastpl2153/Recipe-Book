package com.example.recipebook.model;

import org.sqlite.Function;

import java.sql.SQLException;

/** Класс где переопределяли функцию lower у базы данных. Используется для поиска рецептов
 * так как не получалось с киррилецей еализовать поиск без учетов регистра **/
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
