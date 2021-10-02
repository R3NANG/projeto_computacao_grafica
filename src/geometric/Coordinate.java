package geometric;

public class Coordinate {
    public static double[][] world_to_normalized(int[][] point, int window_width, int window_height) {
        double[][] normalized = new double[2][1];
        normalized[0][0] = (point[0][0]-0)/(window_width-0);
        normalized[1][0] = (point[1][0]-0)/(window_height-0);

        return normalized;
    }

    public static double[][] normalized_to_device(int[][] point, int screen_width, int screen_height) {
        double[][] device_point = new double[2][1];
        device_point[0][0] = Math.round(point[0][0]*(screen_width - 1));
        device_point[1][0] = Math.round(point[1][0]*(screen_height - 1));
        return device_point;
    }
                                
}

