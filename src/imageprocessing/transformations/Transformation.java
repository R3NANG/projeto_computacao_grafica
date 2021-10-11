package imageprocessing.transformations;

import java.awt.image.BufferedImage;
import imageprocessing.Normalization;

public class Transformation {
    public static BufferedImage gamma (int[][] image, float gamma, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = Normalization.pixelNormalization((int)(Math.round(2*Math.pow(image[i][j], gamma))));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage negative (int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = 255 - image[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage logarithm (int[][] image, float constant, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = Normalization.pixelNormalization((int)(Math.round(constant*Math.log10(image[i][j] + 1))));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage linear (int[][] image, int aConstant, int bConstant, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = Normalization.pixelNormalization(Math.round(aConstant*image[i][j] + bConstant));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage dynamicRange (int[][] image, int targetValue, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = Normalization.pixelNormalization(Math.round((image[i][j]/255) * targetValue));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage sigmoid (int[][] image, int greyCenterValue, int sigmaValue, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = Normalization.pixelNormalization(
                        (int)(Math.round(255 * (1 / (1 + Math.exp(-(image[i][j] - greyCenterValue) / sigmaValue))))));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }
}
