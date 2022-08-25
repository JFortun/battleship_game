package com.fortun.model.dto;

import java.util.List;

public class DeployShipsDTO {

    private String playerId;
    private List<ShipDTO> shipsDeploy;

    public DeployShipsDTO(String playerId, List<ShipDTO> shipsDeploy) {
        this.playerId = playerId;
        this.shipsDeploy = shipsDeploy;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public List<ShipDTO> getShipsDeploy() {
        return shipsDeploy;
    }

    public void setShipsDeploy(List<ShipDTO> shipsDeploy) {
        this.shipsDeploy = shipsDeploy;
    }
}
