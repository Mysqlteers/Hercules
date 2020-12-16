package com.hercules.model;

public class CalculationHelper {
    private String jobtype;
    private String material;
    private double cubicarea;

    public CalculationHelper() {
    }

    public CalculationHelper(String jobtype, String material, double cubicarea) {
        this.jobtype = jobtype;
        this.material = material;
        this.cubicarea = cubicarea;
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
