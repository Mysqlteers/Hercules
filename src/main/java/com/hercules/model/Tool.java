package com.hercules.model;

import com.sun.istack.Nullable;

import javax.persistence.*;


@Entity
@Table(name = "tools")
public class Tool
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private Long toolId;

    String serialNr;

    String type;

    String model;

    boolean rented;

    int dailyCost;

    /************************************************ Relations ************************************************************/

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @Nullable
    private Contact info;

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

    public Contact getInfo() {
        return info;
    }

    public void setInfo(Contact info) {
        this.info = info;
    }
}
