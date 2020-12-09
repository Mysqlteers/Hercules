package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "contact_id")
    private Long contactId;
    @Column(name = "case_id")
    private Long caseId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    @JsonManagedReference
    private Set<Person> persons;
    @ManyToMany(mappedBy = "contacts", fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getContacts().add(this);
    }

    public Contact() {
    }

    public Contact(Long contactId, Long caseId, Set<Person> persons, Set<Employee> employees) {
        this.contactId = contactId;
        this.caseId = caseId;
        this.persons = persons;
        this.employees = employees;
    }

    public Contact(Long caseId) {
        this.caseId = caseId;
        this.persons = new HashSet<>();
        this.employees = new HashSet<>();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", caseId=" + caseId +
                ", persons=" + persons +
                '}';
    }
}