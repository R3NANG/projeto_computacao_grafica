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
public class PanelImage extends javax.swing.JPanel {

    /**
     * Creates new form PanelBoard
     */

    private BufferedImage image;
    public PanelImage(){}
    public PanelImage(BufferedImage image) {
        this.image = image;
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
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.white);
    }
    public Dimension getPreferredSize() {
        return new Dimension(355, 445);
    }
}
