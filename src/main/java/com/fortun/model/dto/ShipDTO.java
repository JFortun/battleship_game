package com.fortun.model.dto;

public class ShipDTO {

    private String shipType;
    private String[] coordinates;

    public ShipDTO(String shipType, String[] coordinates) {
        this.shipType = shipType;
        this.coordinates = coordinates;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }
}
