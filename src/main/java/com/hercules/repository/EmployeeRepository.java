package com.hercules.repository;

import com.hercules.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAllByOrderByPositionAscNameAsc();
    Optional<Employee> findEmployeeByPhone(String phone);
}
