package imageprocessing.filters;


import java.awt.image.BufferedImage;
import java.util.Arrays;

import imageprocessing.Normalization;

public class Filter {

    public static BufferedImage mean(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[width][height];
        int normalizationCoefficient = 0;

        // Determinando o coeficiente de normalização
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                normalizationCoefficient += image[i][j];
            }
        }

        // Realizando a soma dos pixels vizinhos
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sum = 0;

                sum += image[i][j];

                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum += image[i - 1][j - 1]/normalizationCoefficient;
                }
                if (i - 1 >= 0) {
                    sum += image[i - 1][j]/normalizationCoefficient;
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    sum += image[i - 1][j + 1]/normalizationCoefficient;
                }
                if (j - 1 >= 0) {
                    sum += image[i][j - 1]/normalizationCoefficient;
                }
                if (j + 1 < width) {
                    sum += image[i][j + 1]/normalizationCoefficient;
                }
                if (i + 1 < height && j - 1 >= 0) {
                    sum += image[i + 1][j - 1]/normalizationCoefficient;
                }
                if (i + 1 < height) {
                    sum += image[i + 1][j]/normalizationCoefficient;
                }
                if (i + 1 < height && j + 1 < width) {
                    sum += image[i + 1][j + 1]/normalizationCoefficient;        
                }

                imageMatrix[i][j] = Math.round(sum);
            }
        }
        
        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage median(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // definindo o array mascara 3X3
                int[] array = new int[9];

                array[0] = image[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    array[1] = image[i - 1][j - 1];
                }
                if (i - 1 >= 0) {
                    array[2] = image[i - 1][j];
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    array[3] = image[i - 1][j + 1];
                }
                if (j - 1 >= 0) {
                    array[4] = image[i][j - 1];
                }
                if (j + 1 < width) {
                    array[5] = image[i][j + 1];
                }
                if (i + 1 < height && j - 1 >= 0) {
                    array[6] = image[i + 1][j - 1];
                }
                if (i + 1 < height) {
                    array[7] = image[i + 1][j];
                }
                if (i + 1 < height && j + 1 < width) {
                    array[8] = image[i + 1][j + 1];
                }

                // Ordenando o array
                Arrays.sort(array);

                // Atribuindo a mediana para o pixel
                imageMatrix[i][j] = array[4];
            }
        }
        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage highPass(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // definindo o array mascara 3X3
                int[] array = new int[9];

                array[0] = image[i][j]*8;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    array[1] = image[i - 1][j - 1]*-1;
                }
                if (i - 1 >= 0) {
                    array[2] = image[i - 1][j]*-1;
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    array[3] = image[i - 1][j + 1]*-1;
                }
                if (j - 1 >= 0) {
                    array[4] = image[i][j - 1]*-1;
                }
                if (j + 1 < width) {
                    array[5] = image[i][j + 1]*-1;
                }
                if (i + 1 < height && j - 1 >= 0) {
                    array[6] = image[i + 1][j - 1]*-1;
                }
                if (i + 1 < height) {
                    array[7] = image[i + 1][j]*-1;
                }
                if (i + 1 < height && j + 1 < width) {
                    array[8] = image[i + 1][j + 1]*-1;
                }

                int sum = Arrays.stream(array).sum();
                imageMatrix[i][j] = Normalization.pixelNormalization(sum);
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage roberts(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int approximationX = 0;
                int approximationY = 0;

                // Aproximação em Y
                if (j + 1 < width) {
                    approximationY = image[i][j] - image[i][j + 1];
                } else {
                    approximationY = image[i][j];
                }
                
                // Aproximação em X
                if (i + 1 < height) {
                    approximationX = image[i][j] - image[i + 1][j];
                } else {
                    approximationX = image[i][j];
                }

                imageMatrix[i][j] = Math.abs(approximationX) + Math.abs(approximationY);
                imageMatrix[i][j] = Normalization.pixelNormalization(imageMatrix[i][j]);
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage prewitt (int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 1; i < height-1; i++) {
            for (int j = 1; j < width-1; j++) {

                // Em direção a X
                int mask0 = image[i - 1][j - 1];
                int mask1 = image[i - 1][j];
                int mask2 = image[i - 1][j + 1];

                int mask3 = image[i + 1][j - 1];
                int mask4 = image[i + 1][j];
                int mask5 = image[i + 1][j + 1];

                // Em direção a Y
                int mask6 = image[i - 1][j - 1];
                int mask7 = image[i][j - 1];
                int mask8 = image[i + 1][j - 1];
                
                int mask9 = image[i - 1][j + 1];
                int mask10 = image[i][j + 1];
                int mask11 = image[i + 1][j + 1];

                imageMatrix[i][j] = 
                    Math.abs((mask0 + mask1 + mask2) - (mask3 + mask4 + mask5)) +
                    Math.abs((mask6 + mask7 + mask8) - (mask9 + mask10+ mask11));
                imageMatrix[i][j] = Normalization.pixelNormalization(imageMatrix[i][j]);
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage robertCrossed(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int approximationX = 0;
                int approximationY = 0;

                // Aproximação em Y
                if (i + 1 < height && j + 1 < width) {
                    approximationY = image[i][j + 1] - image[i + 1][j];
                } else {
                    approximationY = image[i][j];
                }
                
                // Aproximação em X
                if (i + 1 < height && j + 1 < width) {
                    approximationX = image[i][j] - image[i + 1][j + 1];
                } else {
                    approximationX = image[i][j];
                }

                imageMatrix[i][j] = Math.abs(approximationX) + Math.abs(approximationY);
                imageMatrix[i][j] = Normalization.pixelNormalization(imageMatrix[i][j]);
            }
        }
        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage hightBoost(int[][] image, int coefficient, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                // definindo o array mascara 3X3
                int[] array = new int[9];

                array[0] = image[i][j]*(9*coefficient - 1);
                if (i - 1 >= 0 && j - 1 >= 0) {
                    array[1] = image[i - 1][j - 1]*-1;
                }
                if (i - 1 >= 0) {
                    array[2] = image[i - 1][j]*-1;
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    array[3] = image[i - 1][j + 1]*-1;
                }
                if (j - 1 >= 0) {
                    array[4] = image[i][j - 1]*-1;
                }
                if (j + 1 < width) {
                    array[5] = image[i][j + 1]*-1;
                }
                if (i + 1 < height && j - 1 >= 0) {
                    array[6] = image[i + 1][j - 1]*-1;
                }
                if (i + 1 < height) {
                    array[7] = image[i + 1][j]*-1;
                }
                if (i + 1 < height && j + 1 < width) {
                    array[8] = image[i + 1][j + 1]*-1;
                }

                int sum = Arrays.stream(array).sum();
                imageMatrix[i][j] = Normalization.pixelNormalization(sum);
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

    public static BufferedImage sobel(int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int[][] imageMatrix = new int[height][width];

        for (int i = 1; i < height-1; i++) {
            for (int j = 1; j < width-1; j++) {

                // Em direção a X
                int mask0 = image[i - 1][j - 1];
                int mask1 = image[i - 1][j];
                int mask2 = image[i - 1][j + 1];

                int mask3 = image[i + 1][j - 1];
                int mask4 = image[i + 1][j];
                int mask5 = image[i + 1][j + 1];

                // Em direção a Y
                int mask6 = image[i - 1][j - 1];
                int mask7 = image[i][j - 1];
                int mask8 = image[i + 1][j - 1];
                
                int mask9 = image[i - 1][j + 1];
                int mask10 = image[i][j + 1];
                int mask11 = image[i + 1][j + 1];

                imageMatrix[i][j] = 
                    Math.abs((mask0 + 2*mask1 + mask2) - (mask3 + 2*mask4 + mask5)) +
                    Math.abs((mask6 + 2*mask7 + mask8) - (mask9 + 2*mask10 + mask11));
                imageMatrix[i][j] = Normalization.pixelNormalization(imageMatrix[i][j]);
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix); 
    }

}
