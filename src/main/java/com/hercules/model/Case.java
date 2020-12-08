package com.hercules.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cases")
public class Case {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "case_id")
    private Long caseId;

    private String description;

    private int status;

    private String location;

    @Column(name = "case_start_date")
    private String caseStartDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superCase")
    Set<Document> documents = new HashSet<>();


    public String getDescription() {
        return description;
    }

    public Case(Long caseId, String description, int status, String location, String caseStartDate) {
        this.caseId = caseId;
        this.description = description;
        this.status = status;
        this.location = location;
        this.caseStartDate = caseStartDate;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId=" + caseId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", caseStartDate='" + caseStartDate + '\'' +
                '}';
    }

    public Case(String description, int status, String location) {
        this.description = description;
        this.status = status;
        this.location = location;
    }

    public Case() {
    }

    public void addDocument(Case superCase, String documentName, String location){
        Document newDoc = new Document(this,documentName, location);
        documents.add(newDoc);
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public String getCaseStartDate() {
        return caseStartDate;
    }

    public void setCaseStartDate(String caseStartDate) {
        this.caseStartDate = caseStartDate;
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
}
