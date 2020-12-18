package com.hercules.model;

public class CalculationResult {
    private String priceWithoutMarkup;
    private String priceWithMarkup;

    public CalculationResult() {
    }

    public String getPriceWithoutMarkup() {
        return priceWithoutMarkup;
    }

    public void setPriceWithoutMarkup(String priceWithoutMarkup) {
        this.priceWithoutMarkup = priceWithoutMarkup;
    }

    public String getPriceWithMarkup() {
        return priceWithMarkup;
    }

    public void setPriceWithMarkup(String priceWithMarkup) {
        this.priceWithMarkup = priceWithMarkup;
    }
}
