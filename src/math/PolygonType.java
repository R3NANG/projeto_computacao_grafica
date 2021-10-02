package math;

public enum PolygonType {
    PRIMITIVE(0),
    TRANSFORMATION2D(1),
    TRANSFORMATION3D(2);

    private final int code;

    PolygonType(int code) {
        this.code = code;
    }

    public int getPolygonCode() {
        return this.code;
    }

}
