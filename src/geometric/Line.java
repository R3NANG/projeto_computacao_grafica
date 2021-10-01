package geometric;

import java.awt.Graphics;
import math.Polygon;

public class Line {
    public static void bresenham (Polygon polygon, int x1, int y1, int x2, int y2) {
        int delta_x = Math.abs(x2 - x1);
        int delta_y = Math.abs(y2 - y1);

        int mid_point = 2*delta_y - delta_x;

        for (int current_x = x1, current_y = y1; current_x <= x2; ++current_x)
        {

            //g.drawRect(current_x, current_y, 1, 1);
            polygon.insert2D(current_x, current_y);
            if (mid_point > 0)
            {
                ++current_y;
                mid_point += 2*(delta_y - delta_x);
            }
            else
            {
                mid_point += 2*delta_y;
            }
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
            polygon.insert2D(Math.round(current_x), Math.round(current_y));
            current_x += increment_x;
            current_y += increment_y;
        }
    }
}
