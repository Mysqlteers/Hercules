package com.hercules.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hercules.service.utility.S3Loader;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "file_locations")
public class S3File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long fileLocationId;

    @NotNull
    @Column(name = "is_after_picture")
    private boolean isAfterPicture;

    @NotNull
    @Column(name = "location")
    private String location;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    public Long getFileLocationId() {
        return fileLocationId;
    }

    public void setFileLocationId(Long fileLocationId) {
        this.fileLocationId = fileLocationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public S3File(String location, boolean isAfterPicture) {
        this.location = location;
        this.isAfterPicture = isAfterPicture;
    }

    public String imageUrl()
    {
        return S3Loader.getInstance().getS3ObjectUrl(location);
    }



    public S3File() {
    }
}
