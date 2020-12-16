package com.hercules.model;

public class CalculationHelper {
    private String jobtype;
    private String material;
    private double cubicarea;
    private double markup;

    public CalculationHelper() {
    }

    public CalculationHelper(String jobtype, String material, double cubicarea, double markup) {
        this.jobtype = jobtype;
        this.material = material;
        this.cubicarea = cubicarea;
        this.markup = markup;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public double getCubicarea() {
        return cubicarea;
    }

    public void setCubicarea(double cubicarea) {
        this.cubicarea = cubicarea;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
