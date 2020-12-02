package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "file_locations")
public class S3File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "location_id")
    private Long fileLocationId;

    @Column(name = "location")
    private String location;

    @ManyToOne @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task superTask;


    public S3File(String location) {
        this.location = location;
    }

    public S3File() {

    }
}
