package imageprocessing.transformations;

import java.awt.image.BufferedImage;
import imageprocessing.Normalization;

public class Transformation {
    public static BufferedImage gamma (int[][] image, float gamma, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;
        int c = 1;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar efeito 
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //imageMatrix[i][j] = Normalization.pixelNormalization((int)(Math.round(2*Math.pow(image[i][j], gamma))));
                //imageMatrix[i][j] = (int) Math.round(c * Math.pow(image[i][j], gamma));  
                imageMatrix[i][j] = Normalization.pixelNormalization((int)(Math.round(c * Math.pow(image[i][j], gamma))));  
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage negative (int[][] image, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

         // percorre toda imagem pixel a pixel para realizar a transformacao negativa
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica a formula pixel a pixel
                imageMatrix[i][j] = 255 - image[i][j];
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage logarithm (int[][] image, float constant, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

         // percorre toda imagem pixel a pixel para realizar a transformacao logaritmica
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica a formula pixel a pixel
                imageMatrix[i][j] = Normalization.pixelNormalization((int)(Math.round(constant*Math.log10(image[i][j] + 1))));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage linear (int[][] image, int aConstant, int bConstant, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel para realizar a transferência linear
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                // Aplica pixel a pixel
                imageMatrix[i][j] = Normalization.pixelNormalization(Math.round(aConstant*image[i][j] + bConstant));
                //imageMatrix[i][j] = Math.round(aConstant*image[i][j] + bConstant);

            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    public static BufferedImage dynamicRange (int[][] image, int targetValue, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // Valores minimos e máximos da freaquencia
        int fmin = image[0][0];
        int fmax = fmin;

        // Achando os valores mínimos e máximos
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (image[i][j] < fmin) {
                    fmin = image[i][j];
                }
                if (image[i][j] > fmax) {
                    fmax = image[i][j];
                }
            }
        }

        // percorre toda imagem pixel a pixel para realizar a transformacao
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double dividend = image[i][j]-fmin; 
                double divisor = fmax- fmin;
                double division = dividend/divisor;
                //Aplica a transferência de faixa dinâmica: f' = ((f - fmin) / (fmax - fmin))*w 
                imageMatrix[i][j] = (int) Math.round(division * targetValue); 
                //imageMatrix[i][j] = Normalization.pixelNormalization(Math.round((image[i][j]/255) * targetValue));
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }

    // Aplica a transferência de intensidade geral (ITF sigmoide)
    public static BufferedImage sigmoid (int[][] image, int greyCenterValue, int sigmaValue, boolean doNormalize) {
        int width = image[0].length;
        int height = image.length;

        // Criar uma imagem de Buffer para receber manipulações
        int[][] imageMatrix = new int[height][width];

        // percorre toda imagem pixel a pixel
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Aplica pixel a pixel a função S(r) = 255 * (1/(1+e^-((r-w)/sigma))) 
                double s = Math.round(255 * (1 / (1 + Math.pow(Math.E, - ((image[i][j] - greyCenterValue) / sigmaValue)))));
                imageMatrix[i][j] = (int) s;
                /*
                imageMatrix[i][j] = Normalization.pixelNormalization(
                        (int)(Math.round(255 * (1 / (1 + Math.exp(-(image[i][j] - greyCenterValue) / sigmaValue))))));
                        */
            }
        }

        return doNormalize? Normalization.imageNormalization(imageMatrix) : Normalization.matrixToBufferedImage(imageMatrix);
    }
}
