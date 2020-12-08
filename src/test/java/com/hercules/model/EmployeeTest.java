package com.hercules.model;

import com.hercules.service.utility.S3Loader;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeTest {
    @Test
    @Order(1)
    void canCreateEmployeeObject() {
        assertNotNull(new Employee());
    }

    @Test
    @Order(2)
    void canUpdateAndReadEmployeeObject() {
        String testMail = "email.com";
        Employee employee = new Employee();
        employee.setEmail(testMail);
        assertEquals(employee.getEmail(), testMail);
    }

    @Test
    @Order(3)
    void canReadEmployeeImageURL() {
//        Employee employee = new Employee();
//        S3Loader loader = S3Loader.getInstance();
//        loader.uploadImage("C:/Users/emiln/Desktop/image.jpg",
//                          "employee-pictures/test_employee_pic");
//        employee.setPictureLocation("employee-pictures/test_employee_pic");
//        System.out.println("Employee.imageURL = " + employee.imageURL());
    }
}