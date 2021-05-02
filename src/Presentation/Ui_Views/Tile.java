package Presentation.Ui_Views;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    private String name;
    protected static final float SIZE_MULT_WIDTH = (float) 1.16;
    protected static float SIZE_MULT_HEIGHT = (float) 0.95;
    private String myImagePath;
    private ImageIcon myImage;
    public Tile(String name, Color myColor, String pathImage){
        this.name = name;
        this.myImage = new ImageIcon(pathImage);
        this.setBackground(myColor);
        this.setBackground(Color.black);
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
        this.setPreferredSize(new Dimension(Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }
    public String getName(){return this.name;}

    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void setIcon() throws InterruptedException {
        this.myImage = new ImageIcon("Files/drawable/white-key-down.png");
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
//        Thread.sleep(5000);
//        this.myImage = new ImageIcon("Files/drawable/white-key.png");
   //     this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }

    public void backToWhite() {
        this.myImage = new ImageIcon("Files/drawable/white-key.png");
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }
}
