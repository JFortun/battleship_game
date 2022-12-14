package com.fortun.model.dto;

public class FireIDTO {
    private String playerId;
    private String coordinate;

    public FireIDTO(String playerId, String coordinate) {
        this.playerId = playerId;
        this.coordinate = coordinate;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
