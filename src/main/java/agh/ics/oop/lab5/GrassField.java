package agh.ics.oop.lab5;

import agh.ics.oop.WorldMap;
import agh.ics.oop.lab2.MoveDirection;
import agh.ics.oop.lab2.Vector2d;
import agh.ics.oop.lab3.Animal;
import agh.ics.oop.lab4.MapVisualizer;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {
    private int grassFields;
    private final Map<Vector2d, Grass> grassMap;
    private final Map<Vector2d, Object> animalMap;
    private int mapSize = 1;


    public GrassField(int grassFieldsNumber) {
        this.grassFields = grassFieldsNumber;
        this.grassMap = new HashMap<Vector2d, Grass>();
        this.animalMap = new HashMap<Vector2d, Object>();
        setGrass();
    }


    private void setGrass() {
        Random random = new Random();
        int i = 0;
        while (i < this.grassFields) {
            int x = random.nextInt((int) sqrt(this.grassFields * 10));
            int y = random.nextInt((int) sqrt(this.grassFields * 10));
            Vector2d vector2d = new Vector2d(x, y);
            if (this.grassMap.get(vector2d) == null) {
                this.grassMap.put(vector2d, new Grass(vector2d));
                i++;
            }
        }

    }

    @Override
    public String toString(WorldMap map) {
        for (Vector2d key : this.grassMap.keySet()) {
            if (key.x > this.mapSize) {
                this.mapSize = key.x;
            }
            if (key.y > this.mapSize) {
                this.mapSize = key.y;
            }
        }
        for (Vector2d key : this.animalMap.keySet()) {
            if (key.x > this.mapSize) {
                this.mapSize = key.x;
            }
            if (key.y > this.mapSize) {
                this.mapSize = key.y;
            }
        }
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.mapSize, this.mapSize));

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.animalMap.get(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPos = animal.getAnimalPosition();
        if (!canMoveTo(animalPos)) {
            this.animalMap.put(animalPos, animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (this.animalMap.get(position) instanceof Animal || this.grassMap.get(position) instanceof Grass);
    }

    @Override
    public void moveOnMap(MoveDirection direction, Vector2d vector2d) {
        if (this.animalMap.get(vector2d) instanceof Animal) {
            switch (direction) {
                case RIGHT -> ((Animal) this.animalMap.get(vector2d)).setOrientation(((Animal) this.animalMap.get(vector2d)).getOrientation().next());
                case LEFT -> ((Animal) this.animalMap.get(vector2d)).setOrientation(((Animal) this.animalMap.get(vector2d)).getOrientation().previous());

                case FORWARD -> {
                    int fx = vector2d.x + ((Animal) this.animalMap.get(vector2d)).getOrientation().toUnitVector().x;
                    int fy = vector2d.y + ((Animal) this.animalMap.get(vector2d)).getOrientation().toUnitVector().y;
                    Vector2d newVector2d = new Vector2d(fx, fy);
                    if (!(this.animalMap.get(newVector2d) instanceof Animal)) {
                        this.animalMap.put(newVector2d, this.animalMap.get(vector2d));
                        this.animalMap.remove(vector2d);
                    }
                }
                case BACKWARD -> {
                    int fx = vector2d.x - ((Animal) this.animalMap.get(vector2d)).getOrientation().toUnitVector().x;
                    int fy = vector2d.y - ((Animal) this.animalMap.get(vector2d)).getOrientation().toUnitVector().y;
                    Vector2d newVector2d = new Vector2d(fx, fy);
                    if (!(this.animalMap.get(newVector2d) instanceof Animal)) {
                        this.animalMap.put(newVector2d, this.animalMap.get(vector2d));
                        this.animalMap.remove(vector2d);
                    }
                }
            }
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animalMap.get(position) instanceof Animal) {
            return this.animalMap.get(position);
        }
        return this.grassMap.get(position);
    }
}