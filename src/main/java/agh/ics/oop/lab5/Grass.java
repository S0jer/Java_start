package agh.ics.oop.lab5;

import agh.ics.oop.MapElement;
import agh.ics.oop.lab2.Vector2d;

public class Grass implements MapElement {

    private Vector2d grassPosition;

    public Grass(Vector2d vector2d) {
        this.grassPosition = vector2d;
    }

    @Override
    public Vector2d getPosition() {
        return grassPosition;
    }

    @Override
    public String toString() {
        return "*";
    }
}
