package com.hercules.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_pictures")
public class S3EmployeePicture {
    @Id
    @Column(name = "picture_location")
    private String pictureLocation;



    public S3EmployeePicture() {
    }

    public S3EmployeePicture(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }
}
