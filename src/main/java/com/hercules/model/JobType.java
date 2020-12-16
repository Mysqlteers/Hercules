package com.hercules.model;

import javax.persistence.*;

@Entity @Table(name = "job_types")
public class JobType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "job_type_id")
    private Long jobTypeId;
    private boolean material;
    private String description;
    private double price;

    public JobType() {
    }

    public JobType(Long jobTypeId, boolean material, String description, double price) {
        this.jobTypeId = jobTypeId;
        this.material = material;
        this.description = description;
        this.price = price;
    }

    public Long getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Long jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public boolean getMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
