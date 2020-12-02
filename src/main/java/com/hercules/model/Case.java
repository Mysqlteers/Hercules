package com.hercules.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cases")
public class Case {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "case_id")
    private Long caseId;

    private String description;

    private int status;

    private String location;

    private Date case_start_date;

    public String getDescription() {
        return description;
    }

    public Case(Long caseId, String description, int status, String location, Date case_start_date) {
        this.caseId = caseId;
        this.description = description;
        this.status = status;
        this.location = location;
        this.case_start_date = case_start_date;
    }

    public Case(String description, int status, String location, Date case_start_date) {
        this.description = description;
        this.status = status;
        this.location = location;
        this.case_start_date = case_start_date;
    }

    public Case() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
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

    public Date getCase_start_date() { return case_start_date; }

    public void setCase_start_date(Date case_start_date) { this.case_start_date = case_start_date; }
}
