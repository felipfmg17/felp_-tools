package felpo.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Gui {
    
    
    public static File selectDirectory(File path) {
        JFileChooser fc=new JFileChooser(path);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }
    
    public static File selectFile(File path) {
        JFileChooser fc=new JFileChooser(path);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }
    
    public static File[] selectFiles(File path){
        JFileChooser fc=new JFileChooser(path);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        fc.showOpenDialog(null);
        return fc.getSelectedFiles();
    }
    
    public static void setlookAndFeel(){
         try { 
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){ e.printStackTrace();}
    }
    
    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    public static Icon loadIcon(File f, int width, int height){
        try {
            BufferedImage im=ImageIO.read(f);
            im=resizeImage(im, width, height);
            return new ImageIcon(im);
        } catch (Exception e) { e.printStackTrace();}   
        return null;
    }
    
    public static String showInputDialog(String message){
        return JOptionPane.showInputDialog(message);
    }
    
    public static void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
