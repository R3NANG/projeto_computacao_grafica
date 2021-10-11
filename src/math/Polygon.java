package math;

import java.util.Arrays;
import math.PolygonType;

public class Polygon {
    private double[][] polygon;
    private int capacity;

    public Polygon(PolygonType type) {
        this.polygon = polygonGeneration(type);
        this.capacity = 0;
    }

    public double[][] polygonGeneration (PolygonType type) {
        switch (type) {
            case PRIMITIVE:
                return new double[2][20];
            case TRANSFORMATION2D:
                return new double[3][4];
            case TRANSFORMATION3D:
                return new double[3][8]; // ainda determinar a base do 3D
            default:
                return null;
        }
    }

    public double[][] getPolygon() {
        return this.polygon;
    }

    public void setPolygon(double[][] polygon) {
        this.polygon = polygon;
    }

    public int getSize() {
        return this.capacity;
    }

    public void insert2D(int x, int y) {
        if (this.capacity == this.polygon[0].length) {
            for (int i = 0; i < this.polygon.length; i++) {
                this.polygon[i] = Arrays.copyOf(this.polygon[i], this.polygon[0].length*2);
            }
        }

        this.polygon[0][capacity] = (double)x;
        this.polygon[1][capacity] = (double)y;
        this.polygon[2][capacity] = 1; 
        this.capacity++;
    }

    public void insert3D(int x, int y) {
        if (this.capacity == this.polygon[0].length) {
            for (int i = 0; i < this.polygon.length; i++) {
                this.polygon[i] = Arrays.copyOf(this.polygon[i], this.polygon[0].length*2);
            }
        }

        this.polygon[0][capacity] = (double)x;
        this.polygon[1][capacity] = (double)y;
        this.polygon[2][capacity] = 1; 
        this.capacity++;
    }

    public void insertPrimitive(int x, int y)
    {
        if (this.capacity == this.polygon[0].length) {
            for (int i = 0; i < this.polygon.length; i++) {
                this.polygon[i] = Arrays.copyOf(this.polygon[i], this.polygon[0].length*2);
            }
        }

        this.polygon[0][capacity] = x;
        this.polygon[1][capacity] = y;
        
        this.capacity++;
    }

    public void resetPrimitive(){
        this.polygon = polygonGeneration(PolygonType.PRIMITIVE);
        this.capacity = 0;
    }

    public void reset2D() {
        this.polygon = polygonGeneration(PolygonType.TRANSFORMATION2D);
        this.capacity = 0;
    }

    public void reset3D() {
        this.polygon = polygonGeneration(PolygonType.TRANSFORMATION3D);
        this.capacity = 0;
    }
}
