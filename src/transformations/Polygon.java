package transformations;

public class Polygon {
    private double[][] square;

    public Polygon() {
        this.square = new double[3][4];
        this.square[0][0] = 0;
        this.square[1][0] = 0;
        this.square[2][0] = 1;
        this.square[0][1] = 0;
        this.square[1][1] = 20;
        this.square[2][1] = 1;
        this.square[0][2] = 20;
        this.square[1][2] = 20;
        this.square[2][2] = 1;
        this.square[0][3] = 20;
        this.square[1][3] = 0;
        this.square[2][3] = 1;
    
    }

    public double[][] getSquare() {
        return this.square;
    }

    public void setSquare(double[][] square) {
        this.square = square;
    }

    public void reset() {
        this.square[0][0] = 0;
        this.square[1][0] = 0;
        this.square[2][0] = 1;
        this.square[0][1] = 0;
        this.square[1][1] = 20;
        this.square[2][1] = 1;
        this.square[0][2] = 20;
        this.square[1][2] = 20;
        this.square[2][2] = 1;
        this.square[0][3] = 20;
        this.square[1][3] = 0;
        this.square[2][3] = 1;
    }
}
