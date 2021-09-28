package geometric;

import transformations.Polygon;

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
            polygon.insert2D (circle_center_x + point_x, circle_center_y + point_y);
            polygon.insert2D (circle_center_x + point_y, circle_center_y + point_x);
            polygon.insert2D (circle_center_x - point_y, circle_center_y + point_x);
            polygon.insert2D (circle_center_x - point_x, circle_center_y + point_y);
            polygon.insert2D (circle_center_x - point_x, circle_center_y - point_y);
            polygon.insert2D (circle_center_x - point_y, circle_center_y - point_x);
            polygon.insert2D (circle_center_x + point_y, circle_center_y - point_x);
            polygon.insert2D (circle_center_x + point_x, circle_center_y - point_y);

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
        for (float point_x = 0, point_y = radius; point_x <= radius/Math.sqrt(2); ++point_x)
        {
            point_y = (float)Math.sqrt(Math.pow(radius,2) - Math.pow(point_x,2));

            polygon.insert2D (Math.round(circle_center_x + point_x), Math.round(circle_center_y + point_y));
            polygon.insert2D (Math.round(circle_center_x + point_y), Math.round(circle_center_y + point_x));
            polygon.insert2D (Math.round(circle_center_x - point_y), Math.round(circle_center_y + point_x));
            polygon.insert2D (Math.round(circle_center_x - point_x), Math.round(circle_center_y + point_y));
            polygon.insert2D (Math.round(circle_center_x - point_x), Math.round(circle_center_y - point_y));
            polygon.insert2D (Math.round(circle_center_x - point_y), Math.round(circle_center_y - point_x));
            polygon.insert2D (Math.round(circle_center_x + point_y), Math.round(circle_center_y - point_x));
            polygon.insert2D (Math.round(circle_center_x + point_x), Math.round(circle_center_y - point_y));
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

            polygon.insert2D (Math.round(circle_center_x + point_x), Math.round(circle_center_y + point_y));
            polygon.insert2D (Math.round(circle_center_x + point_y), Math.round(circle_center_y + point_x));
            polygon.insert2D (Math.round(circle_center_x - point_y), Math.round(circle_center_y + point_x));
            polygon.insert2D (Math.round(circle_center_x - point_x), Math.round(circle_center_y + point_y));
            polygon.insert2D (Math.round(circle_center_x - point_x), Math.round(circle_center_y - point_y));
            polygon.insert2D (Math.round(circle_center_x - point_y), Math.round(circle_center_y - point_x));
            polygon.insert2D (Math.round(circle_center_x + point_y), Math.round(circle_center_y - point_x));
            polygon.insert2D (Math.round(circle_center_x + point_x), Math.round(circle_center_y - point_y));
        }

    }
}
