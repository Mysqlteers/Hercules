package com.hercules.repository;

import com.hercules.model.Contact;
import com.hercules.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAllByOrderByPositionAscFirstNameAsc();
    Optional<Employee> findEmployeeByPhone(String phone); //method only used in testing
    List<Employee> findAllByContacts_contactIdOrderByPositionAscFirstNameAsc(long contactId);
}
