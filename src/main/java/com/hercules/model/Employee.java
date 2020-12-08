package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hercules.service.utility.S3Loader;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "employee_id")
    private Long employeeId;
    @ManyToMany(cascade = {CascadeType.MERGE})
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
    private String certificates;
    private double wage;

    public String imageURL() {
        return S3Loader.getInstance().getS3ObjectUrl(pictureLocation);
    }

    public Employee() {
    }

    public Employee(Long employeeId, Set<Contact> contacts, String pictureLocation, String firstName, String lastName, String position, String email, String phone, String certificates, double wage) {
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Set<Contact> getContacts() {
        return contacts;
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

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
