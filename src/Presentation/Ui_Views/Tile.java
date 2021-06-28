package Presentation.Ui_Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/** Tile
 *
 * The "Tile" class will contain the different methods and attributes of a piano tile in order to set their color, icon and more
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 */
public class Tile extends JButton {
    private final String name;
    protected static final float SIZE_MULT_WIDTH = (float) 1.16;
    protected static final float SIZE_MULT_HEIGHT = (float) 1.5;
    private Color color;
    private ImageIcon myImage;

    /**
     * Constructor for the Tile, you need to send the basic information about the tile
     * @param name Name of the tile
     * @param myColor Color of the tile
     * @param pathImage Path of the image of the tile
     */
    public Tile(String name, Color myColor, String pathImage){
        this.name = name;
        this.myImage = new ImageIcon(pathImage);
        color = myColor;
        this.setBackground(myColor);
        this.setBackground(Color.black);
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
        this.setPreferredSize(new Dimension(Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }

    /**
     * Gets the name of the tile
     * @return The name of the tile
     */
    public String getName(){return this.name;}

    /**
     * Resizes an icon
     * @param icon Icon to resize
     * @param resizedWidth The new width
     * @param resizedHeight The new Height
     * @return The resized icon
     */
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Sets the icon of a tile to pressed
     */
    public void setIcon(){
        this.myImage = new ImageIcon("Files/drawable/white-key-down.png");
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }

    /**
     * Sets the icon of the tile
     * @param icon The desired icon
     */
    public void setSelectedIcon(ImageIcon icon){
        this.myImage = icon;
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));

    }

    /**
     * Sets the icon of the tile to white key
     */
    public void backToWhite() {
        this.myImage = new ImageIcon("Files/drawable/white-key.png");
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }

    /**
     * Sets the icon of the tile to black key
     */
    public void backToBlack() {
        this.myImage = new ImageIcon("Files/drawable/black-key.png");
        this.setIcon(resizeIcon(this.myImage, Math.round(this.myImage.getIconWidth()*SIZE_MULT_WIDTH), Math.round(this.myImage.getIconHeight()*SIZE_MULT_HEIGHT)));
    }

    /**
     * Gets the color of the tile
     * @return Color. Type of color obtainable
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the tile
     * @param color The new color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
