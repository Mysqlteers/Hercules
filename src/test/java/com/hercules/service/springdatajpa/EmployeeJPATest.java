package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Employee;
import com.hercules.service.ContactService;
import com.hercules.service.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeJPATest {
    //tests repo
    @Autowired
    ContactService cs;

    @Autowired
    EmployeeService es;

    private String TESTPHONE = "tilfældigt nummer";
    private Long TESTCASEID = (long) 9999999;

    @Test
    @Order(1)
    void canSaveAndFindById() {
        Contact testContact = new Contact();
        testContact.setCaseId(TESTCASEID);
        testContact = cs.save(testContact);

        Employee employee = new Employee();
        employee.setFirstName("Bob");
        employee.setPhone(TESTPHONE);

        testContact.getEmployees().add(employee);
        employee.getContacts().add(testContact);


        es.save(employee);
        cs.save(testContact);
        assertTrue(es.findEmployeeByPhone(TESTPHONE).isPresent());
    }

    @Test
    @Order(2)
    void canFindEmployeesAllOrdered() {
        assertTrue(es.findAllByOrderByPositionAscFirstNameAsc().size() >= 1);
    }

    @Test
    @Order(3)
    void canFindById() {
        Employee employee = es.findEmployeeByPhone(TESTPHONE).get();
        Employee otherEmployee = es.findById(employee.getEmployeeId()).get();

        assertEquals(employee.getPhone(), otherEmployee.getPhone());
    }

    @Test
    @Order(4)
    void canUpdateEmployee() {
        String newFirstName = "Hans";
        Employee employee = es.findEmployeeByPhone(TESTPHONE).get();
        employee.setFirstName(newFirstName);
        Employee newEmployee = es.save(employee);

        assertEquals(employee.getFirstName(), newEmployee.getFirstName());
        assertEquals(employee.getEmployeeId(), newEmployee.getEmployeeId());
    }

    @Test
    @Order(5)
    void canDeleteEmployee() {
        int sizeBefore = es.findAll().size();
        es.deleteById(es.findEmployeeByPhone(TESTPHONE).get().getEmployeeId());
        cs.deleteById(cs.findContactByCaseId(TESTCASEID).get().getContactId());
        assertTrue(sizeBefore > es.findAll().size());
    }

    @Test
    @Order(6)
    void canAddEmployeeToContact() {
        Contact contact = new Contact();
        contact.setCaseId((long)2);
        contact = cs.save(contact);

        Employee employee = new Employee();
        employee.setPosition("AAAAQ");
        employee = es.save(employee);

        contact.addEmployee(employee);
        contact = cs.save(contact);
//
//        Employee employee = es.findById((long) 1).get();
////        contact.getEmployees().add(employee);
//        employee.getContacts().add(cs.findById((long) 1).get());
////        cs.save(contact);
//        es.save(employee);
//
////        Contact newContact = cs.findById((long) 1).get();
////        System.out.println(newContact.getEmployees().toString());
        assertTrue(true);
    }
}