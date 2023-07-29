module com.example.recipebook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.recipebook to javafx.fxml;
    exports com.example.recipebook;

    opens com.example.recipebook.controller to javafx.fxml;
    exports  com.example.recipebook.controller;

    requires java.naming;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
}