package com.hercules.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {

    private static Connection conn;


    /**
     * @Author Norsker, Rasmus
     * @return Connection to the DB.
     */

    public static Connection getDatabaseConnection() {
        if (conn != null) return conn;
        String name, pass, url;
        try {

            url = "jdbc:mysql://den1.mysql1.gear.host:3306/herculesdb?autoReconnect=true";
            Class.forName("com.mysql.cj.jdbc.Driver");
            name = "herculesdb";
            pass = "Gu9OM-sE7-7m";
            conn = DriverManager.getConnection(url, name, pass);
            System.out.println("Connection created");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Send fra nano command line");

        }
        return conn;
    }
}