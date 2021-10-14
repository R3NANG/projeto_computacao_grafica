package imageprocessing.transformations;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Histogram {
    public static BufferedImage graph (int[][] image) {
        int width = image[0].length;
        int height = image.length;
        BufferedImage resultingImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int[] freq = new int[256];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = 0;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                freq[image[i][j]] += 1;
            }
        }

        // Coloca todos os pixels do buffered image na cor branca
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                resultingImage.setRGB(i, j, Color.WHITE.getRGB());
                //resultingImage.setRGB(j, i, Color.WHITE.getRGB());
            }
        }

        // Procura o valor RGB com maior frequencia entre 0 e 255
        int max = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > max) {
                max = freq[i];
            }
        }

        // Plota as frequencias dos valores RGB na vertical //altura
        for (int i = 0; i < height - 1; i++) {
            int function = (150 * freq[i]) / max;
            for (int j = 0; j < function; j++) {
                resultingImage.setRGB(i, height - 1 - j, Color.DARK_GRAY.getRGB());
            }
        }
        return resultingImage ;
    }

    public static int[][] equalize (int[][] image) {
        int width = image[0].length;
        int height = image.length;
        float[][] equalizationMatrix = new float[255][6];
        int[][] resultingMatrix = new int[height][width];

        // inicializa o nível de cinza e a frequência dos níveis de cinza
        for (int i = 0; i < equalizationMatrix.length; i++) {
            equalizationMatrix[i][0] = 1000;
            equalizationMatrix[i][1] = 0;
        }

        // Insere os níveis de cinza, incrementando as suas frequências de repetições e calculando rK (valor do pixel / 255)
        for (int i = 0; i < resultingMatrix.length; i++) {
            for (int j = 0; j < resultingMatrix.length; j++) {
                equalizationMatrix[image[i][j]][0] = image[i][j];
                equalizationMatrix[image[i][j]][1] += 1;
                equalizationMatrix[image[i][j]][2] = image[i][j] / 255;
            }
        }

        equalizationMatrix = sortMatrix(equalizationMatrix);

        //calculando Pr(rk)
        int counter = 0;
        while (equalizationMatrix[counter][0] != 1000) {
            //frequência do pixel dividido pela quantidade de pixels
            equalizationMatrix[counter][3] = equalizationMatrix[counter][1] / (255 * 255);
            counter = counter + 1;
        }

        //calculando Sk - Soma acumulada de Pr(rk)
        counter = 0;
        while (equalizationMatrix[counter][0] != 1000) {
            if (counter == 0) {
                equalizationMatrix[counter][4] = equalizationMatrix[counter][3];
            } else {
                equalizationMatrix[counter][4] = equalizationMatrix[counter][3] + equalizationMatrix[counter - 1][4];
            }
            counter = counter + 1;
        }

        //calculando Round(255 * Sk)
        counter = 0;
        while (equalizationMatrix[counter][0] != 1000) {
            equalizationMatrix[counter][5] = (int) Math.round(255 * equalizationMatrix[counter][4]);
            if (equalizationMatrix[counter][5] > 255) {
                equalizationMatrix[counter][5] = 255;
            }
            counter = counter + 1;
        }

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int pixelValue = image[i][j];
                int counter2 = 0;
                while (equalizationMatrix[counter2][0] != pixelValue) {
                    counter2 = counter2 + 1;
                }
                resultingMatrix[i][j] = (int) equalizationMatrix[counter2][5];
            }
        }

        return resultingMatrix;
    }

    public static float[][] sortMatrix (float[][] equalizationMatrix) {
        boolean wasSwap = true;
        while (wasSwap == true) {
            wasSwap = false;
            for (int i = 0; i < equalizationMatrix.length - 1; i++) {
                if (equalizationMatrix[i][0] > equalizationMatrix[i + 1][0]) {
                    float auxiliaryVariable0, auxiliaryVariable1, auxiliaryVariable2, auxiliaryVariable3;
                    auxiliaryVariable0 = equalizationMatrix[i][0];
                    auxiliaryVariable1 = equalizationMatrix[i][1];
                    auxiliaryVariable2 = equalizationMatrix[i][2];
                    auxiliaryVariable3 = equalizationMatrix[i][3];

                    equalizationMatrix[i][0] = equalizationMatrix[i + 1][0];
                    equalizationMatrix[i][1] = equalizationMatrix[i + 1][1];
                    equalizationMatrix[i][2] = equalizationMatrix[i + 1][2];
                    equalizationMatrix[i][3] = equalizationMatrix[i + 1][3];

                    equalizationMatrix[i + 1][0] = auxiliaryVariable0;
                    equalizationMatrix[i + 1][1] = auxiliaryVariable1;
                    equalizationMatrix[i + 1][2] = auxiliaryVariable2;
                    equalizationMatrix[i + 1][3] = auxiliaryVariable3;

                    wasSwap = true;
                }
            }
        }
        return equalizationMatrix;
    }
}
