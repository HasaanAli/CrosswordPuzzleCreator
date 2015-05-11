/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author hasaan
 */
public class PuzzleJPanel extends JPanel {
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);       

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
    }
}
