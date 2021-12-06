package agh.ics.oop.lab1;

import agh.ics.oop.Engine;
import agh.ics.oop.WorldMap;
import agh.ics.oop.lab2.MoveDirection;
import agh.ics.oop.lab2.Vector2d;
import agh.ics.oop.lab3.Animal;
import agh.ics.oop.lab3.OptionsParser;
import agh.ics.oop.lab4.MapVisualizer;
import agh.ics.oop.lab4.RectangularMap;
import agh.ics.oop.lab4.SimulationEngine;

import java.util.List;

public class World extends OptionsParser {
    public static void main(String[] args) {

        String[] arguments = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = new OptionsParser().parse(arguments);
        WorldMap map = new RectangularMap(10, 6);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        Engine enegine = new SimulationEngine(directions, map, positions);
        enegine.run();
        System.out.println(map.toString(map));

    }
}
