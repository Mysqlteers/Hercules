package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Employee;
import com.hercules.repository.EmployeeRepository;
import com.hercules.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeJPA implements EmployeeService {
    private EmployeeRepository er;

    public EmployeeJPA(EmployeeRepository er) {
        this.er = er;
    }

    @Override
    public Set<Employee> findAll() {
        //Skal aldrig bruges
        Set<Employee> set = new HashSet<>();
        er.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Employee save(Employee object) {
        return er.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        er.deleteById(aLong);
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return er.findById(aLong);
    }

    //below methods only for this object/table
    @Override
    public List<Employee> findAllByOrderByPositionAscFirstNameAsc() {
        return er.findAllByOrderByPositionAscFirstNameAsc();
    }

    @Override
    public Optional<Employee> findEmployeeByPhone(String phone) {
        return er.findEmployeeByPhone(phone);
    }

    @Override
    public List<Employee> findAllByContacts_contactIdOrderByPositionAscFirstNameAsc(long contactId) {
        return er.findAllByContacts_contactIdOrderByPositionAscFirstNameAsc(contactId);
    }

}
