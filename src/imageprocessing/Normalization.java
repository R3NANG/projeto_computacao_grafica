package imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage; 

public class Normalization {

    public static BufferedImage imageNormalization(int[][] imageMatrix) {
        int[][] matrixNormalized = matrixNormalization(imageMatrix);
        BufferedImage image = new BufferedImage(imageMatrix[0].length, imageMatrix.length,  BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < matrixNormalized.length; i++) {
            for (int j = 0; j < matrixNormalized[0].length; j++) {
                image.setRGB(j, i, pixelColor(matrixNormalized[i][j]));
            }
        }
        return image;
    }

    public static BufferedImage matrixToBufferedImage(int[][] imageMatrix) {
        BufferedImage image = new BufferedImage(imageMatrix[0].length, imageMatrix.length,  BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < imageMatrix.length; i++) {
            for (int j = 0; j < imageMatrix[0].length; j++) {
                image.setRGB(j, i, pixelColor(imageMatrix[i][j]));
            }
        }
        return image;
    }

    public static int pixelColor(int colorRGB) {
        return new Color(colorRGB, colorRGB, colorRGB).getRGB();
    }

    public static int  pixelNormalization (int pixel) {
        if (pixel > 255) {
            return 255;
        }
        if (pixel < 0) {
            return 0;
        }
        return pixel;
    }

    public static int[][] matrixNormalization(int[][] imageMatrix) {
        int height = imageMatrix.length;
        int width = imageMatrix[0].length;
        int[][] resultingMatrix = new int[height][width];
        int min = imageMatrix[0][0];
        int max =  min;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = imageMatrix[i][j];
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }
        }

        float range = (max - min) == 0 ? 0 : 255 / (max - min);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                resultingMatrix[i][j] = Math.round(range * (imageMatrix[i][j] - min));
            }
        }

        return resultingMatrix;
    }
}
