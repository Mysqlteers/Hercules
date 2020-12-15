package com.hercules.model;

import javax.persistence.*;

@Entity @Table(name = "containers")
public class Container {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "container_id")
    private Long containerId;
    @Column(name = "container_name")
    private String containerName;
    @Column(name = "daily_cost")
    private double dailyCost;
    @Column(name = "number_of_days")
    private int numberOfDays;
    @Column(name = "pick_up_price")
    private double pickUpPrice;
    @Column(name = "is_picked_up")
    private boolean isPickedUp;
    @ManyToOne @JoinColumn(name = "contact_id")
    private Contact contact;

    public Container(Long containerId, String containerName, double dailyCost, int numberOfDays, double pickUpPrice, boolean isPickedUp, Contact contact) {
        this.containerId = containerId;
        this.containerName = containerName;
        this.dailyCost = dailyCost;
        this.numberOfDays = numberOfDays;
        this.pickUpPrice = pickUpPrice;
        this.isPickedUp = isPickedUp;
        this.contact = contact;
    }

    public Container() {
    }

    public double calculateTotal() {
        if (isPickedUp) {
            return (dailyCost * numberOfDays + pickUpPrice);
        } else {
            return (dailyCost * numberOfDays);
        }
    }

    @Override
    public String toString() {
        return "Container{" +
                "containerId=" + containerId +
                ", containerName='" + containerName + '\'' +
                ", dailyCost=" + dailyCost +
                ", numberOfDays=" + numberOfDays +
                ", pickUpPrice=" + pickUpPrice +
                ", isPickedUp=" + isPickedUp +
                ", contact=" + contact +
                '}';
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerNumber) {
        this.containerName = containerNumber;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getPickUpPrice() {
        return pickUpPrice;
    }

    public void setPickUpPrice(double pickUpPrice) {
        this.pickUpPrice = pickUpPrice;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }
}
