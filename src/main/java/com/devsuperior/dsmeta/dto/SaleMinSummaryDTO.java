package com.devsuperior.dsmeta.dto;

public class SaleMinSummaryDTO {

    private String name;
    private Double sum;

    public SaleMinSummaryDTO() {
    }

    public SaleMinSummaryDTO(String name, Double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public Double getSum() {
        return sum;
    }
}
