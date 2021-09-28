package math;

import java.util.Arrays;

public class Polygon {
    private double[][] polygon;
    private int capacity;

    public Polygon() {
        this.polygon = new double[3][4];
        this.capacity = 0;
        this.insert2D(0, 0);
        this.insert2D(0, 20);
        this.insert2D(20, 20);
        this.insert2D(20, 0);
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
                this.polygon[i] = Arrays.copyOf(this.polygon[i], 20);
            }
        }

        this.polygon[0][capacity] = (double)x;
        this.polygon[1][capacity] = (double)y;
        this.polygon[2][capacity] = 1; 

        this.capacity++;
    }

    public void reset2D() {
        this.polygon = new double[3][4];
        this.capacity = 0;
        this.insert2D(0, 0);
        this.insert2D(0, 20);
        this.insert2D(20, 20);
        this.insert2D(20, 0);
    }
}
