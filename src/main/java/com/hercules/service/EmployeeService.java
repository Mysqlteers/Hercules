package com.hercules.service;

import com.hercules.model.Employee;
import com.hercules.model.Person;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends CrudService<Employee, Long> {
    List<Employee> findAllByOrderByPositionAscNameAsc();
    Optional<Employee> findEmployeeByPhone(String phone);
}
