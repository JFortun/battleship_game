package com.fortun.model.enums;

public enum Cell {
    UNKNOWN("UNKNOWN", "·  "),
    SHIP("SHIP", "S  "),
    HIT("HIT", "Ͼ  "),
    MISSED("MISS", "ϴ  "),
    SUNKEN_SHIP("SUNK", "ͳ  ");

    private String state;
    private String symbol;

    Cell(String state, String symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
