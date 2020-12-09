package com.hercules.service;

import com.hercules.model.Contact;
import com.hercules.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends CrudService<Employee, Long> {
    List<Employee> findAllByOrderByPositionAscFirstNameAsc();
    Optional<Employee> findEmployeeByPhone(String phone); //only used in testing
    List<Employee> findAllByContactsOrderByPositionAscFirstNameAsc(Contact contact);
}