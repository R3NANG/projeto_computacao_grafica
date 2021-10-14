/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.Color;
import panels.PanelBoard;
import panels.PencilPanel;
import math.Matrix;
import math.Polygon;
import math.PolygonType;

import main.Main;
import transformations.Transformation3D;

/**
 *
 * @author Renan
 */
public class Panel3D extends javax.swing.JInternalFrame {
    private int assistantX = 0, assistantY = 0, assistantZ = 0;
    private int assistantBX = 0, assistantBY = 0, assistantBZ = 0;
    private Polygon polygon = new Polygon(PolygonType.TRANSFORMATION3D);
    private PanelBoard panelBoard;
    private Transformation3D transformation3D = new Transformation3D();
    
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public void setPanelBoard(PanelBoard panelBoard) {
        this.panelBoard = panelBoard;
        this.panelBoard.setPolygon(this.polygon);
        this.panelBoard.setPencil(new PencilPanel(){
            @Override
            public void draw (PanelBoard board, Graphics g) {
                int cos = (int)Math.round(Math.cos(Math.toRadians(45)));
                int sin = (int)Math.round(Math.sin(Math.toRadians(45)));
                // Axis X
                g.setColor(Color.red);
                g.drawLine(0+cos, board.getCenterY()-sin, board.getWidth()+cos, board.getCenterY()-sin);

                // Axis Y
                g.setColor(Color.green);
                g.drawLine(board.getCenterX()+cos, 0-sin, board.getCenterX()+cos, board.getHeight()-sin);

                // Axis Z

                // Positive
                g.setColor(Color.blue);
                g.drawLine(
                        board.getCenterX(), board.getCenterY(),
                        board.getCenterX() + (int)Math.round(Math.cos(Math.toRadians(45)) * board.getCenterY()),
                        board.getCenterY() - (int)Math.round(Math.sin(Math.toRadians(45)) * board.getCenterY())
                );

                // Negative
                g.drawLine(
                        board.getCenterX(), board.getCenterY(),
                        board.getCenterX() - (int)Math.round(Math.cos(Math.toRadians(45)) * board.getCenterY()),
                        board.getCenterY() + (int)Math.round(Math.sin(Math.toRadians(45)) * board.getCenterY())
                );
            

                // Draw N polygon
                g.setColor(Color.black);


                /*
                // A - B
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][0], (int)board.getPolygon().getPolygon()[1][0],
                        (int)board.getPolygon().getPolygon()[0][1], (int)board.getPolygon().getPolygon()[1][1]
                );

                // B - C 
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][1], (int)board.getPolygon().getPolygon()[1][1],
                        (int)board.getPolygon().getPolygon()[0][2], (int)board.getPolygon().getPolygon()[1][2]
                );

                // C - D
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][2], (int)board.getPolygon().getPolygon()[1][2],
                        (int)board.getPolygon().getPolygon()[0][3], (int)board.getPolygon().getPolygon()[1][3]
                );

                // D - A
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][3], (int)board.getPolygon().getPolygon()[1][3],
                        (int)board.getPolygon().getPolygon()[0][0], (int)board.getPolygon().getPolygon()[1][0]
                );

                // D - E
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][3], (int)board.getPolygon().getPolygon()[1][3],
                        (int)board.getPolygon().getPolygon()[0][4], (int)board.getPolygon().getPolygon()[1][4]
                );

                // E - F
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][4], (int)board.getPolygon().getPolygon()[1][4],
                        (int)board.getPolygon().getPolygon()[0][5], (int)board.getPolygon().getPolygon()[1][5]
                );
                
                // F - G
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][5], (int)board.getPolygon().getPolygon()[1][5],
                        (int)board.getPolygon().getPolygon()[0][6], (int)board.getPolygon().getPolygon()[1][6]
                );
                
                // G - H
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][6], (int)board.getPolygon().getPolygon()[1][6],
                        (int)board.getPolygon().getPolygon()[0][7], (int)board.getPolygon().getPolygon()[1][7]
                );

                // H - E
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][7], (int)board.getPolygon().getPolygon()[1][7],
                        (int)board.getPolygon().getPolygon()[0][4], (int)board.getPolygon().getPolygon()[1][4]
                );

                // C - H
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][2], (int)board.getPolygon().getPolygon()[1][2],
                        (int)board.getPolygon().getPolygon()[0][7], (int)board.getPolygon().getPolygon()[1][7] 
                );

                // B - G
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][1], (int)board.getPolygon().getPolygon()[1][1],
                        (int)board.getPolygon().getPolygon()[0][6], (int)board.getPolygon().getPolygon()[1][6]
                );

                // F - A
                g.drawLine(
                        (int)board.getPolygon().getPolygon()[0][0], (int)board.getPolygon().getPolygon()[1][0],
                        (int)board.getPolygon().getPolygon()[0][5], (int)board.getPolygon().getPolygon()[1][5]
                );
                */
                
                
                //int cos = (int)Math.round(Math.cos(Math.toRadians(45)));
                //int sin = (int)Math.round(Math.sin(Math.toRadians(45)));
                int x = -50;
                int y = 50; 
                int z = 0;
                z = -z;

                int width = 100;
                
                int height = 100;
                int depth = -50;

                //int baseX = board.getCenterX() + x + z;
                //int baseY = board.getCenterY() - y - z;
                int baseX = board.getCenterX();
                int baseY = board.getCenterY();

                Polygon p = board.getPolygon();
                // Point A
                //int aX = baseX + cos;
                //int aY = baseY - sin;

                int aX = (int)(baseX + p.getPolygon()[0][0] + p.getPolygon()[2][0] + cos);
                int aY = (int)(baseY - p.getPolygon()[1][0] - p.getPolygon()[2][0] - sin);

                // Point B
                //int bX = baseX + cos;
                //int bY = baseY - height - sin;
                int bX = (int)(baseX + p.getPolygon()[0][1] + p.getPolygon()[2][1] + cos);
                int bY = (int)(baseY - p.getPolygon()[1][1] - p.getPolygon()[2][1] - sin); 

                // Point C
                //int cX = baseX + width + cos;
                //int cY = baseY - height - sin;
                int cX = (int)(baseX + p.getPolygon()[0][2] + p.getPolygon()[2][2] + cos);
                int cY = (int)(baseY - p.getPolygon()[1][2] - p.getPolygon()[2][2] - sin);

                // Point D
                //int dX = baseX + width + cos;
                //int dY = baseY - sin;
                int dX = (int)(baseX + p.getPolygon()[0][3] + p.getPolygon()[2][3] + cos);
                int dY = (int)(baseY - p.getPolygon()[1][3] - p.getPolygon()[2][3] - sin);

                // Point E
                //int eX = baseX + width + cos*depth;
                //int eY = baseY - sin*depth;
                int eX = (int)(baseX + p.getPolygon()[0][4] + cos*p.getPolygon()[2][4]);
                int eY = (int)(baseY - p.getPolygon()[1][4] - sin*p.getPolygon()[2][4]);

                // Point F
                //int fX = baseX + cos*depth;
                //int fY = baseY - sin*depth;
                int fX = (int)(baseX + p.getPolygon()[0][5] + cos*p.getPolygon()[2][5]);
                int fY = (int)(baseY - p.getPolygon()[1][5] - sin*p.getPolygon()[2][5]);

                // Point G
                //int gX = baseX + cos*depth;
                //int gY = baseY - height - sin*depth;
                int gX = (int)(baseX + p.getPolygon()[0][6] + cos*p.getPolygon()[2][6]);
                int gY = (int)(baseY - p.getPolygon()[1][6] - sin*p.getPolygon()[2][6]);

                // Point H
                //int hX = baseX + width + cos*depth;
                //int hY = baseY - height - sin*depth;
                int hX = (int) (baseX + p.getPolygon()[0][7] + cos*p.getPolygon()[2][6]);
                int hY = (int) (baseY - p.getPolygon()[1][7] - sin*p.getPolygon()[2][6]);

                
                // A - B
                g.drawLine(
                        aX, aY,
                        bX, bY
                );

                // B - C 
                g.drawLine(
                        bX, bY,
                        cX, cY
                );

                // C - D
                g.drawLine(
                        cX, cY,
                        dX, dY
                );

                // D - A
                g.drawLine(
                    dX, dY,
                    aX, aY
                );

                // D - E
                g.drawLine(
                        dX, dY,
                        eX, eY
                );

                // E - F
                g.drawLine(
                        eX, eY,
                        fX, fY
                );
                
                // F - G
                g.drawLine(
                        fX, fY,
                        gX, gY
                );
                
                // G - H
                g.drawLine(
                        gX, gY,
                        hX, hY
                );

                // H - E
                g.drawLine(
                        hX, hY,
                        eX, eY
                );

                // C - H
                g.drawLine(
                        cX, cY,
                        hX, hY
                );

                // B - G
                g.drawLine(
                        bX, bY,
                        gX, gY
                );

                // F - A
                g.drawLine(
                        aX, aY,
                        fX, fY
                );
                
                /*
                g.drawLine(
                        board.getCenterX() + x, board.getCenterY() + y,
                        board.getCenterX() + x + cos * depth, board.getCenterY() + y - sin * depth
                );

                g.drawLine(
                        board.getCenterX() + x + cos, board.getCenterY() + y - sin,
                        board.getCenterX() + x + cos, board.getCenterY() + y - sin * height
                );
                */
                /*
                for (int i = 0; i < board.getPolygon().getSize(); i++)
                {
                    if (i == board.getPolygon().getSize() - 1) {
                            g.drawLine(
                                    board.getCenterX() + (int)board.getPolygon().getPolygon()[0][i],
                                    board.getCenterY() - (int)board.getPolygon().getPolygon()[1][i],
                                    board.getCenterX() + (int)board.getPolygon().getPolygon()[0][0],
                                    board.getCenterY() - (int)board.getPolygon().getPolygon()[1][0]);
                            continue;
                    }
                    g.drawLine(
                            board.getCenterX() + (int)board.getPolygon().getPolygon()[0][i],
                            board.getCenterY() - (int)board.getPolygon().getPolygon()[1][i],
                            board.getCenterX() + (int)board.getPolygon().getPolygon()[0][i+1],
                            board.getCenterY() - (int)board.getPolygon().getPolygon()[1][i+1]);
                }
                */
            }
        });
    }

    /**
     * Creates new form Panel3D
     */
    public Panel3D() {
        initComponents();
        
        transformacoesComboBox.removeAllItems();
        transformacoesComboBox.addItem("Translation");
        transformacoesComboBox.addItem("Scaling");
        transformacoesComboBox.addItem("Rotation");
        transformacoesComboBox.addItem("Reflection");
        transformacoesComboBox.addItem("Shear");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emYLabel = new javax.swing.JLabel();
        x = new javax.swing.JTextField();
        desenharObjetoButton = new javax.swing.JButton();
        aplicarNoObjetoButton = new javax.swing.JButton();
        y = new javax.swing.JTextField();
        transformacoesComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        emXLabel = new javax.swing.JLabel();
        emXText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emYText = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        z = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        emZText = new javax.swing.JTextField();
        emZLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        alturaText = new javax.swing.JTextField();
        larguraText = new javax.swing.JTextField();
        profundidadeText = new javax.swing.JTextField();
        emXBLabel = new javax.swing.JLabel();
        emXBText = new javax.swing.JTextField();
        emYBText = new javax.swing.JTextField();
        emZBText = new javax.swing.JTextField();
        emZBLabel = new javax.swing.JLabel();
        emYBLabel = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Transformações 3D");

        emYLabel.setText("Em Y:");

        x.setText("0");
        x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xActionPerformed(evt);
            }
        });

        desenharObjetoButton.setText("Desenhar Objeto");
        desenharObjetoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desenharObjetoButtonActionPerformed(evt);
            }
        });

        aplicarNoObjetoButton.setText("Aplicar no Objeto");
        aplicarNoObjetoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarNoObjetoButtonActionPerformed(evt);
            }
        });

        y.setText("0");
        y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Objeto 3D");

        jLabel6.setText("Y:");

        jLabel5.setText("Transformações 3D");

        emXLabel.setText("Em X:");

        emXText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emXTextActionPerformed(evt);
            }
        });

        jLabel4.setText("X:");

        emYText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emYTextActionPerformed(evt);
            }
        });

        resetButton.setText("Resetar");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        z.setText("0");
        z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zActionPerformed(evt);
            }
        });

        jLabel7.setText("Z:");

        emZText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emZTextActionPerformed(evt);
            }
        });

        emZLabel.setText("Em Z:");

        jLabel2.setText("Altura:");

        jLabel3.setText("Largura:");

        jLabel8.setText("Profundidade:");

        alturaText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alturaTextActionPerformed(evt);
            }
        });

        larguraText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                larguraTextActionPerformed(evt);
            }
        });

        profundidadeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profundidadeTextActionPerformed(evt);
            }
        });

        emXBLabel.setText("Em X:");

        emXBText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emXBTextActionPerformed(evt);
            }
        });

        emYBText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emYBTextActionPerformed(evt);
            }
        });

        emZBText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emZBTextActionPerformed(evt);
            }
        });

        emZBLabel.setText("Em Z:");

        emYBLabel.setText("Em Y:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(desenharObjetoButton)
                    .addComponent(aplicarNoObjetoButton))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(transformacoesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(resetButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(z, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(larguraText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profundidadeText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alturaText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emXLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emXText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emYLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emYText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emZLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emZText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emXBLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emXBText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emYBLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emYBText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emZBLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emZBText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alturaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(larguraText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(z, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(profundidadeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desenharObjetoButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transformacoesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emXLabel)
                            .addComponent(emXText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emYLabel)
                            .addComponent(emYText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emZLabel)
                            .addComponent(emZText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emXBLabel)
                            .addComponent(emXBText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emYBLabel)
                            .addComponent(emYBText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emZBLabel)
                            .addComponent(emZBText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aplicarNoObjetoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xActionPerformed

    private void desenharObjetoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desenharObjetoButtonActionPerformed
        assistantBX = Integer.parseInt(alturaText.getText());
        assistantBY = Integer.parseInt(larguraText.getText());
        assistantBZ = Integer.parseInt(profundidadeText.getText());

        assistantX = Integer.parseInt(x.getText());
        assistantY = Integer.parseInt(y.getText());
        assistantZ = Integer.parseInt(z.getText());

        int cos = (int)Math.round(Math.cos(Math.toRadians(45)));
        int sin = (int)Math.round(Math.sin(Math.toRadians(45)));
        int x = assistantX;
        int y = assistantY; 
        int z = assistantZ;
        z = -z;

        int height = assistantBX;
        int width = assistantBY;
        int depth = assistantBZ;

        depth = -depth;

        int baseX = panelBoard.getCenterX() + x + z;
        int baseY = panelBoard.getCenterY() - y - z;

        // Point A
        int aX = baseX + cos;
        int aY = baseY - sin;
        polygon.insert3D(x, y, z);


        // Point B
        int bX = baseX + cos;
        int bY = baseY - height - sin;
        polygon.insert3D(x, y+height, z);  


        // Point C
        int cX = baseX + width + cos;
        int cY = baseY - height - sin;
        polygon.insert3D(x+width, y+height, z);


        // Point D
        int dX = baseX + width + cos;
        int dY = baseY - sin;
        polygon.insert3D(x+width, y, z);



        // Point E
        int eX = baseX + width + cos*depth;
        int eY = baseY - sin*depth;
        polygon.insert3D(x+width, y, z+depth);

        // Point F
        int fX = baseX + cos*depth;
        int fY = baseY - sin*depth;
        polygon.insert3D(x, y, z+depth);

        // Point G
        int gX = baseX + cos*depth;
        int gY = baseY - height - sin*depth;
        polygon.insert3D(x, y+height, z+depth);

        // Point H
        int hX = baseX + width + cos*depth;
        int hY = baseY - height - sin*depth;
        polygon.insert3D(x+width, y+height, z+depth);

        /*
        polygon.insert3D(aX, aY);
        polygon.insert3D(bX, bY);
        polygon.insert3D(cX, cY);
        polygon.insert3D(dX, dY);
        polygon.insert3D(eX, eY);
        polygon.insert3D(fX, fY);
        polygon.insert3D(gX, gY);
        polygon.insert3D(hX, hY);
        */
        panelBoard.repaint();

    }//GEN-LAST:event_desenharObjetoButtonActionPerformed

    private void aplicarNoObjetoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarNoObjetoButtonActionPerformed
        // TODO add your handling code here:
        if(transformacoesComboBox.getSelectedItem().equals("Translation")) {
            assistantX = Integer.parseInt(emXText.getText());
            assistantY = Integer.parseInt(emYText.getText());
            assistantZ = Integer.parseInt(emZText.getText());
            assistantZ = -assistantZ;

            Matrix.show (polygon.getPolygon());
            polygon.setPolygon(transformation3D.translation(polygon.getPolygon(), assistantX, assistantY, assistantZ));
            System.out.println("==============================================");
            Matrix.show (polygon.getPolygon());
            panelBoard.repaint();
        } else if(transformacoesComboBox.getSelectedItem().equals("Scaling")) {
            assistantX = Integer.parseInt(emXText.getText());
            assistantY = Integer.parseInt(emYText.getText());
            assistantZ = Integer.parseInt(emZText.getText());
            assistantZ = -assistantZ;
            
            Matrix.show (polygon.getPolygon());
            polygon.setPolygon(transformation3D.scaling(polygon.getPolygon(), assistantX, assistantY, assistantZ));
            System.out.println("==============================================");
            Matrix.show (polygon.getPolygon());
            panelBoard.repaint();
        } else if(transformacoesComboBox.getSelectedItem().equals("Rotation")) {
            assistantX = Integer.parseInt(emXText.getText());
            
            Matrix.show (polygon.getPolygon());
            polygon.setPolygon(transformation3D.rotation(polygon.getPolygon(), assistantX, title));
            System.out.println("==============================================");
            Matrix.show (polygon.getPolygon());
            panelBoard.repaint();
        } else if(transformacoesComboBox.getSelectedItem().equals("Reflection")) {
            Matrix.show (polygon.getPolygon());
            polygon.setPolygon(transformation3D.reflection(polygon.getPolygon(), emXText.getText()));
            System.out.println("==============================================");
            Matrix.show (polygon.getPolygon());
            panelBoard.repaint();
        } else if(transformacoesComboBox.getSelectedItem().equals("Shear")) {
            assistantX = Integer.parseInt(emXText.getText());
            assistantY = Integer.parseInt(emYText.getText());
            assistantZ = Integer.parseInt(emZText.getText());
            
            assistantZ = -assistantZ;

            assistantBX = Integer.parseInt(emXBText.getText());
            assistantBY = Integer.parseInt(emYBText.getText());
            assistantBZ = Integer.parseInt(emZBText.getText());
            
            assistantBZ = -assistantBZ;

            Matrix.show (polygon.getPolygon());
            polygon.setPolygon(transformation3D.shear(polygon.getPolygon(), 
                        assistantX, assistantBX, assistantY, assistantBY, assistantZ, assistantBZ));
            System.out.println("==============================================");
            Matrix.show (polygon.getPolygon());
            panelBoard.repaint();
        }
    }//GEN-LAST:event_aplicarNoObjetoButtonActionPerformed

    private void yActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yActionPerformed

    private void transformacoesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transformacoesComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transformacoesComboBoxActionPerformed

    private void emXTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emXTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emXTextActionPerformed

    private void emYTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emYTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emYTextActionPerformed

    private void transformacoesComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_transformacoesComboBoxItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Translation")) {
            emXLabel.setText("Em X:");
            emXLabel.setVisible(true);
            emXText.setVisible(true);
            emYLabel.setText("Em Y:");
            emYLabel.setVisible(true);
            emYText.setVisible(true);
            emZLabel.setText("Em Z:");
            emZLabel.setVisible(true);
            emZText.setVisible(true);
            
            emXBLabel.setVisible(false);
            emXBText.setVisible(false);
            emYBLabel.setVisible(false);
            emYBText.setVisible(false);
            emZBLabel.setVisible(false);
            emZBText.setVisible(false);
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Scaling")) {
            emXLabel.setText("Em X:");
            emXLabel.setVisible(true);
            emXText.setVisible(true);
            emYLabel.setText("Em Y:");
            emYLabel.setVisible(true);
            emYText.setVisible(true);
            emZLabel.setText("Em Z:");
            emZLabel.setVisible(true);
            emZText.setVisible(true);
            
            emXBLabel.setVisible(false);
            emXBText.setVisible(false);
            emYBLabel.setVisible(false);
            emYBText.setVisible(false);
            emZBLabel.setVisible(false);
            emZBText.setVisible(false);
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Rotation")) {
            emXLabel.setText("Rotação:");
            emXLabel.setVisible(true);
            emXText.setVisible(true);
            emYLabel.setText("Em Y:");
            emYLabel.setVisible(false);
            emYText.setVisible(false);
            emZLabel.setText("Em Z:");
            emZLabel.setVisible(false);
            emZText.setVisible(false);
            
            emXBLabel.setVisible(false);
            emXBText.setVisible(false);
            emYBLabel.setVisible(false);
            emYBText.setVisible(false);
            emZBLabel.setVisible(false);
            emZBText.setVisible(false);
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Reflection")) {
            emXLabel.setText("Reflexão (X, Y, O):");
            emXLabel.setVisible(true);
            emXText.setVisible(true);
            emYLabel.setText("Em Y:");
            emYLabel.setVisible(false);
            emYText.setVisible(false);
            emZLabel.setText("Em Z:");
            emZLabel.setVisible(false);
            emZText.setVisible(false);
            
            emXBLabel.setVisible(false);
            emXBText.setVisible(false);
            emYBLabel.setVisible(false);
            emYBText.setVisible(false);
            emZBLabel.setVisible(false);
            emZBText.setVisible(false);
        } else if(evt.getStateChange() == ItemEvent.SELECTED && transformacoesComboBox.getSelectedItem().equals("Shear")) {
            emXLabel.setText("AX:");
            emXLabel.setVisible(true);
            emXText.setVisible(true);
            emYLabel.setText("AY:");
            emYLabel.setVisible(true);
            emYText.setVisible(true);
            emZLabel.setText("AZ:");
            emZLabel.setVisible(true);
            emZText.setVisible(true);
            
            emXBLabel.setText("BX:");
            emXBLabel.setLocation(108, 239);
            emXBLabel.setVisible(true);
            emXBText.setLocation(136, 233);
            emXBText.setVisible(true);
            emYBLabel.setText("BY:");
            emYBLabel.setLocation(108, 273);
            emYBLabel.setVisible(true);
            emYBText.setLocation(146, 267);
            emYBText.setVisible(true);
            emZBLabel.setText("BZ:");
            emZBLabel.setLocation(108, 307);
            emZBLabel.setVisible(true);
            emZBText.setLocation(146, 301);
            emZBText.setVisible(true);
        }
    }//GEN-LAST:event_transformacoesComboBoxItemStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        polygon.reset3D();
        panelBoard.repaint();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void zActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zActionPerformed

    private void emZTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emZTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emZTextActionPerformed

    private void alturaTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alturaTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alturaTextActionPerformed

    private void larguraTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_larguraTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_larguraTextActionPerformed

    private void profundidadeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profundidadeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profundidadeTextActionPerformed

    private void emXBTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emXBTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emXBTextActionPerformed

    private void emYBTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emYBTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emYBTextActionPerformed

    private void emZBTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emZBTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emZBTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alturaText;
    private javax.swing.JButton aplicarNoObjetoButton;
    private javax.swing.JButton desenharObjetoButton;
    private javax.swing.JLabel emXBLabel;
    private javax.swing.JTextField emXBText;
    private javax.swing.JLabel emXLabel;
    private javax.swing.JTextField emXText;
    private javax.swing.JLabel emYBLabel;
    private javax.swing.JTextField emYBText;
    private javax.swing.JLabel emYLabel;
    private javax.swing.JTextField emYText;
    private javax.swing.JLabel emZBLabel;
    private javax.swing.JTextField emZBText;
    private javax.swing.JLabel emZLabel;
    private javax.swing.JTextField emZText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField larguraText;
    private javax.swing.JTextField profundidadeText;
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox<String> transformacoesComboBox;
    private javax.swing.JTextField x;
    private javax.swing.JTextField y;
    private javax.swing.JTextField z;
    // End of variables declaration//GEN-END:variables
}
