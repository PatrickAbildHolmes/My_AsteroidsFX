package dk.sdu.cbse.common;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    public String type; // for logic pertaining collision behavior
    public int health;

    public String getID() {
        return ID.toString();
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {  // Always pass the intended direction (rather than just change in value),
        this.rotation = rotation;               // e.g. entity.setRotation(entity.getRotation+change)
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {this.type = type;}
    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}
}
