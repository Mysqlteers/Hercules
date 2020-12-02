package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "file_locations")
public class S3File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "location_id")
    private Long fileLocationId;

    @NotNull
    @Column(name = "location")
    private String location;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;


    public S3File(String location) {
        this.location = location;
    }

    public S3File()
    {
    }
}
