package imageprocessing.transformations.morphology;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Morphology {
    /**
     *  Operação de dilatação morfológica binaria.
     */
    public static int[][] binaryDilation (int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;

        //Cria a imagem de saída
        BufferedImage outputImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //matriz auxiliar
        int[][] binaryImage  = new int[height][width];

        int positionX = 0;
        int positionY = 0;
        int p0 = 0;
        int p255 = 0;
        int pNew = 0;
        int pNew2 = 255;

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127)
        // Pun (limiar 51), Kapur (limiar 162)
        // Percorrendo toda imagem pixel a pixel para transformar em binária.
        for (int x = 0; x < height; x++) { 
             for (int y = 0; y < width; y++) {
                 if (imageMatrix[x][y] >= 127) {
                      imageMatrix[x][y] = 255;
                      p255++;
                 } else {
                     imageMatrix[x][y] = 0;
                     p0++;
                 }
             }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        // Percorrendo toda imagem pixel a pixel para realizar dilatação
        for (int x = 0; x < imageMatrix.length - 1; x++) {
            for (int y = 0; y < imageMatrix[x].length; y++) {
                 // Só aplica em pixels ativos
                 if (imageMatrix[x][y] == pNew) {
                     // soma a posição do pixel pelo elemento estruturante
                      for (int xEE = 0; xEE < structuringElement.length; xEE++) {
                          for (int yEE = 0; yEE < structuringElement[xEE].length - 1; yEE++) {
                              // Somando a posição de x, y na imagem com a posição de x, y do elemento estruturante
                              positionX = x + structuringElement[xEE][yEE];
                              positionY = y + structuringElement[xEE][yEE + 1];
                              // se essa posição já está com pixel ativo, nao muda nada
                              if (positionX >= 0 && positionY >= 0 && positionX <= 256 && positionY <= 256 &&
                                      binaryImage[positionX][positionY] != pNew) {
                                   binaryImage[positionX][positionY] = pNew;
                              }

                              // atribui a outputImage pixel a pixel o resultado obtido em "matrizImagemBinaria[x][y]"
                              outputImage.setRGB(positionX, positionY, toColor(
                                          binaryImage[positionX][positionY],
                                          binaryImage[positionX][positionY],
                                          binaryImage[positionX][positionY]));
                          }
                      }
                 } else {
                     binaryImage[x][y] = pNew2;
                 }
            }
        }
        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *  Operação de erosão morfológica binaria.
     */

    public static int[][] binaryErosion (int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        int[][] binaryImage = new int[height][width];

        int positionX = 0;
        int positionY = 0;
        int p0 = 0;
        int p255 = 0;
        int pNew = 0;
        int pNew2 = 255;

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).

        // Percorrendo toda imagem pixel a pixel para transformar em binária
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                 if (imageMatrix[x][y] >= 127) {
                      imageMatrix[x][y] = 255;
                      p255++;
                 } else {
                    imageMatrix[x][y] = 0;
                    p0++;
                 }
            }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        //percorre toda imagem pixel a pixel para realizar dilatacao
        for (int x = 0; x < imageMatrix.length - 1; x++) {
            for (int y = 0; x < imageMatrix[x].length; y++) {
                // so aplica em pixels ativos
                if (imageMatrix[x][y] == pNew) {
                    // soma a posicao do pixel pelo elemento estruturante
                    for (int xEE = 0; xEE < structuringElement.length; xEE++) {
                         for (int yEE = 0; yEE < structuringElement[xEE].length - 1; yEE++) {
                             // somando a posicao de x, y na imagem com a posicao de x, y do elemento estruturante
                             positionX = x - structuringElement[xEE][yEE];
                             positionY = y - structuringElement[xEE][yEE + 1];
                             // se essa posicao é pixel ativo, se torna inativo
                             if (positionX >= 0 && positionY >= 0 && positionX <= 255 && positionY <= 255 &&
                                     imageMatrix[positionX][positionY] == pNew) {
                                 binaryImage[positionX][positionY] = pNew;
                             } else {
                                 //desativando pixel
                                 binaryImage[x][y] = pNew2;
                             }
                         }
                    }
                } else {
                    binaryImage[x][y] = pNew2;
                }
            }
        }

        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *  Operação de fechamento morfológico binário.
     */
    public static int[][] binaryClosure (int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        //matriz auxiliar
        int[][] binaryImage = new int[height][width]; 

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        // Percorrendo toda imagem pixel a pixel para transformar em binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                } else {
                    imageMatrix[x][y] = 0;
                }
            }
        }

        int[][] partial = binaryDilation(imageMatrix, structuringElement);
        binaryImage = binaryErosion(partial, structuringElement);
        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *   Operação de abertura morfológica binária.
     */
    public static int[][] binaryOpening(int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        //matriz auxiliar
        int[][] binaryImage = new int[height][width]; 

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        // Percorrendo toda imagem pixel a pixel para transformar em binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                } else {
                    imageMatrix[x][y] = 0;
                }
            }
        }

        int[][] partial = binaryErosion(imageMatrix, structuringElement);
        binaryImage = binaryDilation(partial, structuringElement);

        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *   Operação de hit-or-miss binária.
     */
    public static int[][] binaryHitormiss(int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;

        int p0 = 0;
        int p255 = 0;
        int pNew = 0;
        int pNew2 = 255;

        //matrizes auxiliares
        int[][] binaryImage = new int[height][width];
        int[][] auxiliaryMatrix = new int[height][width];

        int[][] partial = binaryErosion(imageMatrix, structuringElement);
        //elemento estruturante que engloba o inicial
        int[][] newStructuringElement = new int[11][2];
        newStructuringElement[0][0] = -1;
        newStructuringElement[0][1] = -2;
        newStructuringElement[1][0] = -1;
        newStructuringElement[1][1] = -1;
        newStructuringElement[2][0] = -1;
        newStructuringElement[2][1] = 0;
        newStructuringElement[3][0] = -1;
        newStructuringElement[3][1] = 1;
        newStructuringElement[4][0] = -1;
        newStructuringElement[4][1] = 2;
        newStructuringElement[5][0] = 0;
        newStructuringElement[5][1] = 2;
        newStructuringElement[6][0] = 1;
        newStructuringElement[6][1] = -2;
        newStructuringElement[7][0] = 1;
        newStructuringElement[7][1] = -1;
        newStructuringElement[8][0] = 1;
        newStructuringElement[8][1] = 0;
        newStructuringElement[9][0] = 1;
        newStructuringElement[9][1] = 1;
        newStructuringElement[10][0] = 1;
        newStructuringElement[10][1] = 2;

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        // Percorrendo toda imagem pixel a pixel para transformar em binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                } else {
                    imageMatrix[x][y] = 0;
                }
            }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        // fazer a imagem complemento da original binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] == pNew) {
                    auxiliaryMatrix[x][y] = pNew2;
                    p255++;
                } else {
                    auxiliaryMatrix[x][y] = pNew;
                    p0++;
                }
            }
        }

        int[][] partial2 = binaryErosion(auxiliaryMatrix, newStructuringElement);

        for (int i = 0; i < partial2.length; i++) {
            for (int j = 0; j < partial2[i].length; j++) {
                if (partial[i][j] == pNew && partial2[i][j] == pNew) {
                    binaryImage[i][j] = pNew;
                } else {
                    binaryImage[i][j] = pNew2;
                }
            }
        }

        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *  Operação de contorno externo binário.
     */
    public static int[][] binaryOuterContour(int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        int p0 = 0;
        int p255 = 0;
        int pNew = 0;
        int pNew2 = 255;

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        //percorre toda imagem pixel a pixel para transformar em binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                    p255++;
                } else {
                    imageMatrix[x][y] = 0;
                    p0++;
                }
            }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        int[][] partial = binaryDilation(imageMatrix, structuringElement);

        // retirando os pixels que pertencem a imagem original da parcial
        for (int i = 0; i < imageMatrix.length; i++) {
            for (int j = 0; j < imageMatrix[i].length; j++) {
                if (imageMatrix[i][j] == pNew && partial[i][j] == pNew) {
                    partial[i][j] = pNew2;
                }
            }
        }

        //exibirBin(partial, imageMatrix);
        return partial;
    }

    /**
     *  Operação de contorno interno binario.
     */
    public static int[][] binaryInnerContour(int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        int p0 = 0;
        int p255 = 0;
        int pNew = 0;
        int pNew2 = 255;

        //matriz auxiliar
        int[][] binaryImage = new int[height][width];
        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        // Percorre toda imagem pixel a pixel para transformar em binaria

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                    binaryImage[x][y] = 255;
                    p255++;
                } else {
                    imageMatrix[x][y] = 0;
                    binaryImage[x][y] = 0;
                    p0++;
                }
            }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        int[][] partial = binaryErosion(imageMatrix, structuringElement);
        // retirando os pixels ativos que pertencem a imagem parcial da original
        for (int i = 0; i < imageMatrix.length; i++) {
            for (int j = 0; j < imageMatrix[i].length; j++) {
                if (imageMatrix[i][j] == pNew && partial[i][j] == pNew) {
                    binaryImage[i][j] = pNew2;
                }
            }
        }

        //exibirBin(binaryImage, imageMatrix);
        return binaryImage;
    }

    /**
     *  Operação de gradiente morfológico binario.
     */

    public static int[][] binaryMorphologicalGradient (int[][] imageMatrix, int[][] structuringElement) {
        int width = imageMatrix[0].length;
        int height = imageMatrix.length;
        int p0 = 0;
        int p255 = 0;
        int pNew= 0;
        int pNew2 = 255;

        // Média Padrão (limiar 127), Nível Médio de Cinza (limiar 94), 10% de Preto (limiar 28),
        // Seleção Iterativa (limiar 82), Otsu (limiar 83), Dois Picos (limiar 109), Borda (limiar 127),
        // Pun (limiar 51), Kapur (limiar 162).
        // //percorre toda imagem pixel a pixel para transformar em binaria
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (imageMatrix[x][y] >= 127) {
                    imageMatrix[x][y] = 255;
                    p255++;
                } else {
                    imageMatrix[x][y] = 0;
                    p0++;
                }
            }
        }

        if (p0 > p255) {
            pNew = 255;
            pNew2 = 0;
        }

        int[][] partial = binaryDilation(imageMatrix, structuringElement);
        int[][] partial2 = binaryErosion(imageMatrix, structuringElement);

        // retirando os pixels ativos que pertencem a imagem parcial2 da parcial
        for (int i = 0; i < imageMatrix.length; i++) {
            for (int j = 0; j < imageMatrix[i].length; j++) {
                if (partial2[i][j] == pNew && partial[i][j] == pNew) {
                    partial[i][j] = pNew2;
                }
            }
        }
        //exibirBin(partial, imageMatrix);
        return partial;
    }


    /**
     * Operação de erosão morfológica.
     * Nesta operação buscamos entre o pixel e seus vizinhos aqueles com o tom de cinza mais escuro (de menor valor).
     * Os pixels considerados na busca são aqueles marcados com true no kernel.
     */
    public BufferedImage erosion (BufferedImage img, boolean[][] kernel) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //Percorre a imagem de entrada
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //A erosão busca pelo pixel de menor valor
                int min = 255;
                //Para cada pixel percorrido na imagem, precisamos percorrer os seus 8 vizinhos
                //Os vizinhos que serão considerados estão marcados como true no kernel
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        //Observe que os índices de kx e ky variam de 0 até 2. Já os vizinhos de x seriam
                        //x+(-1), x+0 + x+1. Por isso, subtraímos 1 de kx e ky para chegar no vizinho.
                        int px = x + (kx-1);
                        int py = y + (ky-1);

                        //Nas bordas, px ou py podem acabar caindo fora da imagem. Quando isso ocorre, pulamos para o
                        // próximo pixel.
                        if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
                            continue;
                        }

                        //Obtem o tom de cinza do pixel
                        int tone = new Color(img.getRGB(px, py)).getRed();

                        //Se ele for mais escuro que o menor já encontrado, substitui
                        if (kernel[kx][ky] && tone < min) {
                            min = tone;
                        }
                    }
                }

                //Define essa cor na imagem de saída.
                out.setRGB(x, y, new Color(min, min, min).getRGB());
            }
        }
        return out;
    }

    /**
     * Operação de dilatação morfológica.
     * Nesta operação buscamos entre o pixel e seus vizinhos aqueles com o tom de cinza mais claro (de maior valor).
     * Os pixels considerados na busca são aqueles marcados com true no kernel (pixels ativos).
     */
    public BufferedImage dilation (BufferedImage img, boolean[][] kernel) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //Percorre a imagem de entrada
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //A dilatação busca pelo pixel de maior valor
                int max = 0;
                //Para cada pixel percorrido na imagem, precisamos percorrer os seus 9 vizinhos
                //Os vizinhos que serão considerados estão marcados como true no kernel
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        //Observe que os índices de kx e ky variam de 0 até 2. Já os vizinhos de x seriam
                        //x+(-1), x+0 + x+1. Por isso, subtraímos 1 de kx e ky para chegar no vizinho.
                        int px = x + (kx-1);
                        int py = y + (ky-1);

                        //Nas bordas, px ou py podem acabar caindo fora da imagem. Quando isso ocorre, pulamos para o
                        // próximo pixel.
                        if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
                            continue;
                        }

                        //Obtem o tom de cinza do pixel
                        int tone = new Color(img.getRGB(px, py)).getRed();

                        //Se ele for mais claro que o maior já encontrado, substitui
                        if (kernel[kx][ky] && tone > max) {
                            max = tone;
                        }
                    }
                }

                //Define essa cor na imagem de saída.
                out.setRGB(x, y, new Color(max, max, max).getRGB());
            }
        }
        return out;
    }
    
    /**
     * Abertura morfológica.
     * Trata-se de várias erosões seguidas do mesmo número de dilatações. Isso faz com que áreas pequenas da imagem
     * tendam a desaparecer, e estruturas maiores sejam mantidas.
     */
    public BufferedImage opening (BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage c = dilate(erode(img, times, kernel), times, kernel);
        //exibir(c.getRaster());
    	return c;
    }

    /**
     * Fechamento morfológico.
     * Trata-se de várias dilatações seguidas do mesmo número de erosões. Isso faz com que "buracos" pequenos na imagem
     * tendam a desaparecer.
     */
    public BufferedImage closure (BufferedImage img, int times, boolean[][] kernel) {
    	BufferedImage c = erode(dilate(img, times, kernel), times, kernel);
    	//exibir(c.getRaster());
    	return c;
    }
    
    /**
     * Aplica a dilatação times vezes.
     */
    public BufferedImage dilate(BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage out = img;
        for (int i = 0; i < times; i++) {
            out = dilation(out, kernel);
        }
        //exibir(out.getRaster());
        return out;
    }

    /**
     * Aplica a erosao times vezes.
     */
    public BufferedImage erode(BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage out = img;
        for (int i = 0; i < times; i++) {
            out = erosion(out, kernel);
        }
        //exibir(out.getRaster());
        return out;
    }
    

	 /**
     * Garante que o valor do pixel estará entre 0 e 255.
     */
    private static int clamp(float value) {
        int v = (int)value;
        return v > 255 ? 255 : (v < 0 ? 0 : v);
    }

    /**
     * Converte os valores de r, g e b para o inteiro da cor.
     * Os valores podem estar fora do intervalo de 0 até 255, pois
     * a função ajusta chamando a função clamp (acima).
     */
    private static int toColor(float r, float g, float b) {
        return new Color(clamp(r), clamp(g), clamp(b)).getRGB();
    }
}
