package imageprocessing.operators;

import java.awt.image.BufferedImage;
import imageprocessing.Normalization;

public class Operator {

    public static BufferedImage addition (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];


        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica a adição pixel a pixel
                imageMatrix[i][j] = image1[i][j] + image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage subtraction (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
         // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];
        
        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = image1[i][j] - image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage multiplication (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica a multiplicação pixel a pixel
                imageMatrix[i][j] = image1[i][j] * image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage division (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica a divisão pixel a pixel
                if (image2[i][j] != 0) {
                    imageMatrix[i][j] = image1[i][j] / image2[i][j];
                } else {
                    imageMatrix[i][j] = image1[i][j];
                }
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage AND (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
        
         // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica o AND pixel a pixel
                imageMatrix[i][j] = image1[i][j] & image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage OR (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica o OR pixel a pixel
                imageMatrix[i][j] = image1[i][j] | image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage XOR (int[][] image1, int[][] image2, boolean doNormalize) {
        int width = image1[0].length;
        int height = image1.length;
         // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar o efeito
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica o XOR pixel a pixel
                imageMatrix[i][j] = image1[i][j] ^ image2[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }
}
