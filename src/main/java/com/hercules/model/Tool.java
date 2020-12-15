package com.hercules.model;

import javax.persistence.*;


@Entity
@Table(name = "tools")
public class Tool
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private Long toolId;

    //sagsnr, ejet, serie nr, type, model, daglig omkostning


    String serialNr;

    String type;

    String model;

    boolean rented;

    int dailyCost;

    /************************************************ Relations ************************************************************/
    //todo test if works
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /********************************************** Constructors ************************************************************/
    public Tool() {
    }

/******************************************* Getters and setters ************************************************************/

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getSerialNr() {
        return serialNr;
    }

    public void setSerialNr(String serialNr) {
        this.serialNr = serialNr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public int getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(int dailyCost) {
        this.dailyCost = dailyCost;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
