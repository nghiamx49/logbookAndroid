package com.example.logbookform;


import java.io.Serializable;

@SuppressWarnings("serial")
public class FormData implements Serializable {
    private String propertyType;
    private String bedRoom;
    private String addingDate;
    private String monthlyRentPrice;
    private String furnitureType;
    private String notes;
    private String reporterName;

    public FormData() {
        propertyType = "";
        bedRoom = "";
        addingDate = "";
        monthlyRentPrice = "";
        furnitureType = "";
        notes = "";
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(String bedRoom) {
        this.bedRoom = bedRoom;
    }

    public String getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(String addingDate) {
        this.addingDate = addingDate;
    }

    public String getMonthlyRentPrice() {
        return monthlyRentPrice;
    }
    public void setMonthlyRentPrice(String monthlyRentPrice) {
        this.monthlyRentPrice = monthlyRentPrice;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
}
