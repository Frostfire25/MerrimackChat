package com.merrimackchat_client.gui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mark Case
 */
public class Screenshot {
    
    myGUI get = new myGUI();
 
    // Prints contents of a panel to file locatiion of users choice    
    public void makePanelImage(Component panel) {
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(
                size.width, size.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);

        JFileChooser getFile = new JFileChooser();
        getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        // Filter files
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.Images", "jpg", "png");
        getFile.addChoosableFileFilter(filter1);
        int res = getFile.showSaveDialog(null);
        
        if (res == JFileChooser.APPROVE_OPTION) {

            File selFile1 = getFile.getSelectedFile();

            String path1 = selFile1.getAbsolutePath();
            try {
                ImageIO.write(image, "png", selFile1); // with extension filter, jpg and .images work too
                JOptionPane.showMessageDialog(get.getChatPanel(), "Panel saved as Image.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
} // end class Screenshot
