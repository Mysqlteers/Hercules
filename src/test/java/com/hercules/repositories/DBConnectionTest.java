//package com.hercules.repositories;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DBConnectionTest {
//
//    @Test
//    void getDatabaseConnection(){
//        Connection conn = DBConnection.getDatabaseConnection();
//        if (conn==null)
//            System.out.println("Error : no connection to database");
//        assert conn != null;
//    }
//}