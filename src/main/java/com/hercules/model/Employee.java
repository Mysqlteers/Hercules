package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hercules.service.utility.S3Loader;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "employee_id")
    private Long employeeId;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_case",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "contact_id")}
    )
    Set<Contact> contacts = new HashSet<>();
    private String pictureLocation;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String email;
    private String phone;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    @JsonManagedReference
    private Set<Document> certificates = new HashSet<>();
    private double wage;

    public Employee() {
    }

    public Employee(Long employeeId, Set<Contact> contacts, String pictureLocation, String firstName, String lastName, String position, String email, String phone, Set<Document> certificates, double wage) {
        this.employeeId = employeeId;
        this.contacts = contacts;
        this.pictureLocation = pictureLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.certificates = certificates;
        this.wage = wage;
    }

    public void addDocument(Document newDoc){
        newDoc.setEmployee(this);
        certificates.add(newDoc);
    }

    public void removeDocument(Document document){
        certificates.remove(document);
        document.setEmployee(null);
    }

    public List<Document> getDocumentsAsList(){
        if (certificates == null){
            certificates = new HashSet<>();
        }
        ArrayList<Document> myList = new ArrayList<Document>(certificates);
        return myList;
    }

    public String imageURL() {
        //get full link of picture
        return S3Loader.getInstance().getS3ObjectUrl(pictureLocation);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", contacts=" + contacts +
                ", pictureLocation='" + pictureLocation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", certificates='" + certificates + '\'' +
                ", wage=" + wage +
                '}';
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictures) {
        this.pictureLocation = pictures;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Document> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Document> certificates) {
        this.certificates = certificates;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
