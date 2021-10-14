package imageprocessing.transformations.morphology;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Morphology {
    /**
     * Operação de erosão morfológica.
     * Nesta operação buscamos entre o pixel e seus vizinhos aqueles com o tom de cinza mais escuro (de menor valor).
     * Os pixels considerados na busca são aqueles marcados com true no kernel.
     */
    public static BufferedImage erosion (BufferedImage img, boolean[][] kernel) {
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
    public static BufferedImage dilation (BufferedImage img, boolean[][] kernel) {
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
    public static BufferedImage opening (BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage c = dilate(erode(img, times, kernel), times, kernel);
        //exibir(c.getRaster());
    	return c;
    }

    /**
     * Fechamento morfológico.
     * Trata-se de várias dilatações seguidas do mesmo número de erosões. Isso faz com que "buracos" pequenos na imagem
     * tendam a desaparecer.
     */
    public static BufferedImage closure (BufferedImage img, int times, boolean[][] kernel) {
    	BufferedImage c = erode(dilate(img, times, kernel), times, kernel);
    	//exibir(c.getRaster());
    	return c;
    }
    
    /**
     * Aplica a dilatação times vezes.
     */
    public static BufferedImage dilate(BufferedImage img, int times, boolean[][] kernel) {
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
    public static BufferedImage erode(BufferedImage img, int times, boolean[][] kernel) {
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
