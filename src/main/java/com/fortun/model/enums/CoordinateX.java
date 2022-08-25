package com.fortun.model.enums;

public enum CoordinateX {
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E"),
    F(6, "F"),
    G(7, "G"),
    H(8, "H"),
    I(9, "I"),
    J(10, "J");

    private int position;
    private String letter;

    CoordinateX(int position, String letter) {
        this.position = position;
        this.letter = letter;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public static int getPositionFromXCoordinate(String coordinate) {
        CoordinateX coordinateMatched = null;
        for (CoordinateX coordinateX : CoordinateX.values()) {
            if (coordinate.equals(coordinateX.getLetter())) {
                coordinateMatched = coordinateX;
            }
        }

        assert coordinateMatched != null;
        return coordinateMatched.getPosition();
    }
}
