package geometric;

import math.Polygon;

public class Circle {
    public static void mid_point_circle (
            Polygon polygon,
            int circle_center_x, 
            int circle_center_y,
            int radius)
    {
        int mid_point = 1 - radius;

        for (int point_x = 0, point_y = radius; point_x <= point_y; ++point_x)
        {
            polygon.insertPrimitive (circle_center_x + point_x, circle_center_y + point_y);
            polygon.insertPrimitive (circle_center_x + point_y, circle_center_y + point_x);
            polygon.insertPrimitive (circle_center_x - point_y, circle_center_y + point_x);
            polygon.insertPrimitive (circle_center_x - point_x, circle_center_y + point_y);
            polygon.insertPrimitive (circle_center_x - point_x, circle_center_y - point_y);
            polygon.insertPrimitive (circle_center_x - point_y, circle_center_y - point_x);
            polygon.insertPrimitive (circle_center_x + point_y, circle_center_y - point_x);
            polygon.insertPrimitive (circle_center_x + point_x, circle_center_y - point_y);

            if (mid_point < 0) {
                mid_point += 2*point_x + 3;
            } else {
                mid_point += 2*(point_x - point_y) + 5;
                --point_y;
            }
        }
    }

    public static void polynomial_circle (
            Polygon polygon,
            int circle_center_x,
            int circle_center_y,
            int radius)
    {
        for (float point_x = -radius, point_y = radius; point_x <= radius; ++point_x)
        {

            polygon.insertPrimitive(
                    (int)(Math.round(circle_center_x + point_x)), 
                    (int)(Math.round(circle_center_y - Math.sqrt(Math.pow(radius,2) - Math.pow(point_x,2)))));

            polygon.insertPrimitive(
                    (int)(Math.round(circle_center_x + point_x)), 
                    (int)(Math.round(circle_center_y - (-1 * Math.sqrt(Math.pow(radius,2) - Math.pow(point_x,2))))));
            /*
            */
        }
    }

    public static void trigonometric_circle (
            Polygon polygon,
            int circle_center_x,
            int circle_center_y,
            int radius)
    {
        for (float theta = 0; theta <= 45; theta += 0.4)
        {
            float point_x = radius*(float)Math.cos(theta);
            float point_y = radius*(float)Math.sin(theta);

            polygon.insertPrimitive (Math.round(circle_center_x + point_x), Math.round(circle_center_y + point_y));
            polygon.insertPrimitive (Math.round(circle_center_x + point_y), Math.round(circle_center_y + point_x));
            polygon.insertPrimitive (Math.round(circle_center_x - point_y), Math.round(circle_center_y + point_x));
            polygon.insertPrimitive (Math.round(circle_center_x - point_x), Math.round(circle_center_y + point_y));
            polygon.insertPrimitive (Math.round(circle_center_x - point_x), Math.round(circle_center_y - point_y));
            polygon.insertPrimitive (Math.round(circle_center_x - point_y), Math.round(circle_center_y - point_x));
            polygon.insertPrimitive (Math.round(circle_center_x + point_y), Math.round(circle_center_y - point_x));
            polygon.insertPrimitive (Math.round(circle_center_x + point_x), Math.round(circle_center_y - point_y));
        }

    }

    public static void midPointEllipse(
            Polygon polygon,
            float xc, float yc,
            float rx, float ry) 
    {

        float dx, dy, d1, d2, x, y;
        x = 0;
        y = ry;

        // Initial decision parameter of region 1
        d1 = (ry * ry) - (rx * rx * ry) + (0.25f * rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        // For region 1
        while (dx < dy)
        {

            // Print points based on 4-way symmetry
            polygon.insertPrimitive (Math.round(x + xc), Math.round(y + yc));
            polygon.insertPrimitive (Math.round(-x + xc), Math.round(y + yc));
            polygon.insertPrimitive (Math.round(x + xc), Math.round(-y + yc));
            polygon.insertPrimitive (Math.round(-x + xc),Math.round(-y + yc));

            // Checking and updating value of
            // decision parameter based on algorithm
            if (d1 < 0)
            {
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            }
            else
            {
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Decision parameter of region 2
        d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))
            + ((rx * rx) * ((y - 1) * (y - 1)))
            - (rx * rx * ry * ry);

        // Plotting points of region 2
        while (y >= 0) {

            // printing points based on 4-way symmetry
            polygon.insertPrimitive (Math.round(x + xc), Math.round(y + yc));
            polygon.insertPrimitive (Math.round(-x + xc),Math.round(y + yc));
            polygon.insertPrimitive (Math.round(x + xc),Math.round(-y + yc));
            polygon.insertPrimitive (Math.round(-x + xc),Math.round(-y + yc));

            // Checking and updating parameter
            // value based on algorithm
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            }
            else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
    }
}
