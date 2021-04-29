package Presentation.Ui_Views;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    private String name;
    private float SIZE_MULT = (float) 1.5;
    private String myImagePath;
    private ImageIcon myImage;
    public Tile(String name, Color myColor, String pathImage){
        this.name = name;
        this.myImage = new ImageIcon(pathImage);
        this.setBackground(myColor);
        this.setBackground(Color.black);
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT), Math.round(this.myImage.getIconHeight()*SIZE_MULT)));
        this.setPreferredSize(new Dimension(Math.round(this.myImage.getIconWidth()*SIZE_MULT), Math.round(this.myImage.getIconHeight()*SIZE_MULT)));
    }
    public String getName(){return this.name;}
    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
