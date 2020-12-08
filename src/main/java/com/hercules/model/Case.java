package com.hercules.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cases")
public class Case implements Taskable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "case_id")
    private Long caseId;

    private String description;

    private int status;

    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
    private Set<Task> subtasks;

    @Column(name = "case_start_date")
    private String caseStartDate;

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

    @Override
    public List<Taskable> getSubtasksAsList() {
        List<Taskable> result = new ArrayList<>(subtasks);
        return result;
    }

    @Override
    public void addTask(Taskable task) {
        subtasks.add((Task)task);
    }

    @Override
    public double getPercentageDone() {
        return 0;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getEst_time() {
        return "";
    }

    @Override
    public String getDeadline() {
        return LocalDate.now().toString();
    }

    @Override
    public Long getId() {
        return getCaseId();
    }

    @Override
    public String getStartDate() {
        return getCaseStartDate();
    }
}
