package com.example.recipebook.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection {
    public static Connection connection(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database\\recipebook.db");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
