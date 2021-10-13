/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.net.URL;


import panels.PanelImageFilters;
import panels.PanelImage;

/**
 *
 * @author Renan
 */
public class PanelImageFilters extends javax.swing.JInternalFrame {
    private PanelImage panelImageInput;
    private BufferedImage image;
    private int[][] imageMatrix;
    private int[][] imageMatrix1;
    private int[][] imageMatrix2; 
    private int imgWidth;
    private int imgHeight;
    private int imgValorMaximo;

    public void setPanelImageInput(PanelImage panel) {
        this.panelImageInput = panel;
    }
    /**
     * Creates new form PanelImageFilters
     */
    public PanelImageFilters() {
        initComponents();
        
        transformacoesComboBox.removeAllItems();
        transformacoesComboBox.addItem("Media");
        transformacoesComboBox.addItem("Mediana");
        transformacoesComboBox.addItem("Passa alta basico");
        transformacoesComboBox.addItem("Operador de Roberts");
        transformacoesComboBox.addItem("Operador de Roberts Cruzado");
        transformacoesComboBox.addItem("Operador de Prewitt");
        transformacoesComboBox.addItem("Alto Reforco (Hight-Boost)");
        transformacoesComboBox.addItem("Operador de Sobel");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transformacoesComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        aplicarNoObjetoButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelImageOriginal = new javax.swing.JPanel();
        selecionarImgButton = new javax.swing.JButton();

        setClosable(true);
        setTitle("Filtros de Imagem");

        transformacoesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        transformacoesComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                transformacoesComboBoxItemStateChanged(evt);
            }
        });
        transformacoesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transformacoesComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtros de Imagem");

        aplicarNoObjetoButton.setText("Aplicar no Objeto");
        aplicarNoObjetoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarNoObjetoButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Selecionar Imagem");

        javax.swing.GroupLayout panelImageOriginalLayout = new javax.swing.GroupLayout(panelImageOriginal);
        panelImageOriginal.setLayout(panelImageOriginalLayout);
        panelImageOriginalLayout.setHorizontalGroup(
            panelImageOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        panelImageOriginalLayout.setVerticalGroup(
            panelImageOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        selecionarImgButton.setText("Selecionar");
        selecionarImgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarImgButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(transformacoesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(aplicarNoObjetoButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(selecionarImgButton))))
                .addGap(93, 93, 93)
                .addComponent(panelImageOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImageOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transformacoesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aplicarNoObjetoButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selecionarImgButton)))
                .addContainerGap(194, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transformacoesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transformacoesComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transformacoesComboBoxActionPerformed

    private void transformacoesComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_transformacoesComboBoxItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Media")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Mediana")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Passa alta basico")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Operador de Roberts")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Operador de Roberts Cruzado")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Operador de Prewitt")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Alto Reforco (Hight-Boost)")) {
            
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Operador de Sobel")) {
            
        }
    }//GEN-LAST:event_transformacoesComboBoxItemStateChanged

    private void aplicarNoObjetoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarNoObjetoButtonActionPerformed
        // TODO add your handling code here:
        if(transformacoesComboBox.getSelectedItem().equals("Media")) {
            
        } else if(transformacoesComboBox.getSelectedItem().equals("Mediana")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Passa alta basico")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Operador de Roberts")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Operador de Roberts Cruzado")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Operador de Prewitt")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Alto Reforco (Hight-Boost)")) {

        } else if(transformacoesComboBox.getSelectedItem().equals("Operador de Sobel")) {

        }
    }//GEN-LAST:event_aplicarNoObjetoButtonActionPerformed

    private void selecionarImgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarImgButtonActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("src/images/"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PGM Images", "pgm");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(aplicarNoObjetoButton);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //imageMatrix1 = createImage(fileChooser.getSelectedFile());
                //populaImgInPanel(imageMatrix1, panelImageOriginal);
               // createImage(fileChooser.getSelectedFile());
               
                File path = fileChooser.getSelectedFile();
                System.out.println(path);
                createImage(path.getAbsolutePath());
                //btAplicaFiltro.setEnabled(true);
                //panelImgOutput.repaint();
                //panelImageOriginal.setImage(imgT);

                panelImageInput.setImage(image);
                panelImageInput.repaint();
                panelImageOriginal.getGraphics().drawImage(image, 0, 0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "OPS! N�o foi possivel carregar a imagem.");
        }
    }//GEN-LAST:event_selecionarImgButtonActionPerformed

    /**
     *  Ler o arquivo pgm e monta a popula a matriz imagem
     */
    public int[][] createImage(String path) {
        /*
        FileInputStream fileInputStream = null;
        Scanner scan = null;
        try {
            fileInputStream = new FileInputStream(file);
            scan = new Scanner(fileInputStream);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PanelOperacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Descarta a primeira linha
        scan.nextLine();
        // Read pic width, height and max value
        imgWidth = scan.nextInt();
        imgHeight = scan.nextInt();
        imgValorMaximo = scan.nextInt();
        */ 
        //BufferedImage bimg = null;
        int width;
        int height;

        InputStream inputStream = null;
        Scanner scan = null;
        try {

            System.out.println(path);
            inputStream = new FileInputStream(path);
            scan = new Scanner(inputStream);

            // Descarta a primeira linha
            scan.nextLine();
            // Read pic width, height and max value
            width = scan.nextInt();
            height = scan.nextInt();
            imgValorMaximo = scan.nextInt();

            BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            System.out.printf ("W : %d\n", width);
            System.out.printf ("H : %d\n", height);

            //width = i.getWidth();
            //height = i.getHeight();
            /*
            if (path == null)
                System.out.println("File Null");
            if (inputStream == null) 
                System.out.println("input Null");
            if (bimg == null)
                System.out.println("bimg Null");

            width = bimg.getWidth();
            height = bimg.getHeight();
            */
            //this.image = bimg;

            /**
            * Monta a matriz imagem com os pixels da imagem selecionada
            */
            imageMatrix = new int[width][height];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    // Popula a matriz com os pixels da imagem
                    imageMatrix[row][col] = scan.nextInt();
                }
            }
            inputStream.close();

            /**
            * Monta a matriz imagem com os pixels da imagem selecionada
            */
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    // Prepara a imagem para ser desenhada no jpanel
                    bimg.setRGB(col, row, getCorPixel(imageMatrix[row][col]));
                }
            }

            this.image = bimg;


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "OPS! criar a imagem.");
        }


        /*
        try {
            fileInputStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(PanelOperacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        return imageMatrix;
    }

    /**
     * Exibe a imagem no jPanel
     *
     */
    public void populaImgInPanel(int[][] img, JPanel imgPanel) {
        /**
         * Monta a matriz imagem com os pixels da imagem selecionada
         */
        BufferedImage imagemInput = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < img.length; row++) {
            for (int col = 0; col < img[0].length; col++) {
                // Prepara a imagem para ser desenhada no jpanel
                imagemInput.setRGB(col, row, getCorPixel(imageMatrix[row][col]));
            }
        }
        /**
         * Exibe a imagem no jpanel
         */
        //panelImageOriginal.setImage(imagemInput);
        panelImageOriginal.getGraphics().drawImage(imagemInput, 75, 75, null);
        //panelImageOriginal.repaint();
    }

    /**
     * Retorna o valor em RGB de acordo com o valor do pixel
     */
    private int getCorPixel(int corRGB) {
        return new Color(corRGB, corRGB, corRGB).getRGB();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aplicarNoObjetoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelImageOriginal;
    private javax.swing.JButton selecionarImgButton;
    private javax.swing.JComboBox<String> transformacoesComboBox;
    // End of variables declaration//GEN-END:variables
}
