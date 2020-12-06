package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

    public Contact() {
    }

    public Contact(Long contactId, Long caseId, Set<Person> persons) {
        this.contactId = contactId;
        this.caseId = caseId;
        this.persons = persons;
    }

    public Contact(Long caseId) {
        this.caseId = caseId;
        this.persons = new TreeSet<>();
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