package com.hercules.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
//@Table(name = "cases")
public class Case {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "case_id")
    private int caseId;

    private String description;

    private int status;

    private String location;

    public String getDescription() {
        return description;
    }

    public Case(int caseId, String description, int status, String location) {
        this.caseId = caseId;
        this.description = description;
        this.status = status;
        this.location = location;
    }

    public Case(String description, int status, String location) {
        this.description = description;
        this.status = status;
        this.location = location;
    }

    public Case() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
