/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author hasaan
 */
public class HelpfulStaticFunctions {
        public static void locateAtCentre(JFrame jFrame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width/2-jFrame.getSize().width/2, dim.height/2-jFrame.getSize().height/2);
        }

}
