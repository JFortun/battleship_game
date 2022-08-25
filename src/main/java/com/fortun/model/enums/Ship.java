package com.fortun.model.enums;

public enum Ship {
    Destroyer("Destroyer", 2),
    Submarine("Submarine", 3),
    Cruiser("Cruiser", 3),
    Battleship("Battleship", 4),
    AircraftCarrier("AircraftCarrier", 5);

    private String name;
    private int size;

    Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
