package geometric;

import java.awt.Graphics;
import math.Polygon;

public class Line {
    // Defining region codes
    public static final int INSIDE = 0;
    public static final int LEFT   = 1;
    public static final int RIGHT  = 2;
    public static final int BOTTOM = 4;
    public static final int TOP    = 8;

    // Defining x_max, y_max and x_min, y_min for
    // clipping rectangle. Since diagonal points are
    // enough to define a rectangle
    public static int xMin;
    public static int xMax;
    public static int yMin;
    public static int yMax;


    public static void bresenham (Polygon polygon, int x1, int y1, int x2, int y2) {
        /*
        */
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double rozdil = dx - dy;
        int posun_x, posun_y;
        // Determinando Incremento
        if (x1 < x2) {
            posun_x = 1;
        } else {
            posun_x = -1;
        } 
        if (y1 < y2) {
            posun_y = 1;
        } else {
            posun_y = -1;
        }
        int count = 0;
        polygon.insertPrimitive(x1, y1);
        //Desenha a reta, fazendo o somat�rio em x e y.
        while ((x1 != x2) || (y1 != y2)) {
            double p = 2 * rozdil;
            if (p > -dy) {
                rozdil = rozdil - dy;
                x1 = x1 + posun_x;
            }
            if (p < dx) {
                rozdil = rozdil + dx;
                y1 = y1 + posun_y;
            }

            polygon.insertPrimitive(x1, y1);
        }
    }

    public static void digital_differential_analyzer (Polygon polygon, float x1, float y1, float x2, float y2) {
        float delta_x = x2 - x1;
        float delta_y = y2 - y1;

        float absolute_x = Math.abs(delta_x);
        float absolute_y = Math.abs(delta_y);

        float step = absolute_x > absolute_y? absolute_x : absolute_y;

        float increment_x = delta_x/step;
        float increment_y = delta_y/step;

        float current_x = x1;
        float current_y = y1;

        for (int i = 0; i <= step; ++i)
        {
            // g.drawRect(Math.round(current_x), Math.round(current_y), 1, 1);
            polygon.insertPrimitive(Math.round(current_x), Math.round(current_y));
            current_x += increment_x;
            current_y += increment_y;
        }
    }

    // Implementing Cohen-Sutherland algorithm
    // Clipping a line from P1 = (x2, y2) to P2 = (x2, y2)
    public static void cohenSutherlandClip(
            Polygon polygon,
            int x3, int y3, int x4, int y4)
    {
        // Execute line clipping using Cohen-Sutherland
        int x0 = x3;
        int y0 = y3;
        int x1 = x4;
        int y1 = y4;

        // Compute region codes for P1, P2
        int outCode0 = computeOutCode(x0, y0);
        int outCode1 = computeOutCode(x1, y1);

        boolean accept = false;

        while (true) {
            if ((outCode0 | outCode1) == 0) { // Bitwise OR is 0. Trivially accept
                accept = true;
                break;
            } else if ((outCode0 & outCode1) != 0) { // Bitwise AND is not 0. Trivially reject
                break;
            } else {
                int x, y;

                // Pick at least one point outside rectangle
                int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;

                // Now find the intersection point;
                // use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
                if ((outCodeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
                    y = yMax;
                } else if ((outCodeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                    y = yMin;
                } else if ((outCodeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                    x = xMax;
                } else {
                    y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                    x = xMin;
                }

                // Now we move outside point to intersection point to clip
                if (outCodeOut == outCode0) {
                    x0 = x;
                    y0 = y;
                    outCode0 = computeOutCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    outCode1 = computeOutCode(x1, y1);
                }
            }
        }

        if (accept) {
            polygon.resetPrimitive();
            digital_differential_analyzer(polygon, x0, y0, x1, y1);
        }
        //return null;
    }


    // Define clipping rectangle. Since diagonal points are
    // enough to define a rectangle
    public static void clippingArea(int minX, int minY, int maxX, int maxY) {
        xMin = minX;
        yMin = minY;
        xMax = maxX;
        yMax = maxY;
    }

    private static int computeOutCode(double x, double y) {
        int code = INSIDE;

        if (x < xMin) {
            code |= LEFT;
        } else if (x > xMax) {
            code |= RIGHT;
        }
        if (y < yMin) {
            code |= BOTTOM;
        } else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }
}
