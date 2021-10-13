/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Renan
 */
public class PanelImage extends javax.swing.JInternalFrame {

    /**
     * Creates new form PanelBoard
     */

    private JPanel panel;
    private BufferedImage image;
    public PanelImage() {

        panel = new JPanel() {
            @Override
            public void paint (Graphics g) {

                g.drawImage(image, 0, 0, null);
            }
        };
        getContentPane().add(panel);
        setVisible(true);

        initComponents();
        repaint();

    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    public JPanel getPanel() {
        return this.panel;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getImage() {
        return this.image;
    }

    public int getWidth() {
        return super.getWidth();
    }

    public int getHeight() {
        return super.getHeight();
    }

    public int getCenterX() {
        return super.getWidth()/2;
    }

    public int getCenterY() {
        return super.getHeight()/2;
    }
    
   public void paint(Graphics g) {
       super.paint(g);
       this.setBackground(Color.white);
       
       /*
       // Draw Axis
       g.setColor(Color.red);
       g.drawLine(0, super.getHeight()/2, super.getWidth(), super.getHeight()/2);

       g.setColor(Color.green);
       g.drawLine(super.getWidth()/2, 0, super.getWidth()/2, super.getHeight());
        */

       //g2d.drawImage(image, 0, 0, null);

       this.panel.repaint();
   }

   public Dimension getPreferredSize() {
      return new Dimension(355, 445);
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setTitle("Board");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}