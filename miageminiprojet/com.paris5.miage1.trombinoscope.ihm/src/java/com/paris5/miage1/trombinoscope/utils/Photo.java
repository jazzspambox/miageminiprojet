/* ------------------------------------------------------------------------
 
    Licensed under the Educational Community License version 1.0

    This Original Work, including software, source code, documents,
    or other related items, is being provided by the copyright holder(s)
    subject to the terms of the Educational Community License. By
    obtaining, using and/or copying this Original Work, you agree that you
    have read, understand, and will comply with the following terms and
    conditions of the Educational Community License:

    Permission to use, copy, modify, merge, publish, distribute, and
    sublicense this Original Work and its documentation, with or without
    modification, for any purpose, and without fee or royalty to the
    copyright holder(s) is hereby granted, provided that you include the
    following on ALL copies of the Original Work or portions thereof,
    including modifications or derivatives.

 ------------------------------------------------------------------------ */

package com.paris5.miage1.trombinoscope.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Permet de redimentionner et de decouper une partie d une image
 * @author Mourad, Saliou, Idir
 */
public class Photo {
    
    private int width;
    private int height;
    File origine;
    File destination;
    private String image_ext;

    /**
     * constructeur
     * 
     * @param image_url String dossier de stockage des images
     * @param image_dest nom de l image a travailler
     * @param image_name nom de l image de
     * @throws IOException  l image a generer
     */
    public Photo(String dir, String origine, String image_name) throws IOException {
        
        this.origine = new File(dir+origine);
        BufferedImage img = ImageIO.read( this.origine);
        width = img.getWidth();
        height = img.getHeight();
        String ext[] = origine.split("\\.");
        this.image_ext = ext.length>0? ext[1].toLowerCase() : "";
        this.destination = new File(dir+Configuration.Photo.PATH+File.separator+image_name+'.'+this.image_ext);
    }

    /**
     * redimensionne une image
     * 
     * @param targetWidth int largeur
     * @param targetHeight int hauteur
     * @throws IOException 
     */
    public void resize(int targetWidth, int targetHeight) throws IOException {
        BufferedImage img = ImageIO.read(origine);
        double scaleW = (double) targetWidth / (double) img.getWidth();
        double scaleH = (double) targetHeight / (double) img.getHeight();
        double scale = scaleW < scaleH ? scaleW : scaleH;

        BufferedImage result = new BufferedImage((int) (img.getWidth() * scale),
                (int) (img.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(img, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();
        
        if(ImageIO.write(result, image_ext, origine)){
            this.width = result.getWidth();
            this.height = result.getHeight();
        }
    }
    
    /**
     * largeur de l image
     * @return int
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * hauteur de l image
     * @return int
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * decoupe l'image
     * 
     * @param x int position x point 1
     * @param y int position y point 1
     * @param x2 int position x point 2
     * @param y2 int position y2 point 2
     */
    public void crop(int x, int y, int x2, int y2) throws IOException {
        BufferedImage img = ImageIO.read(origine);
        BufferedImage result = img.getSubimage(x, y, x2, y2);
        if(ImageIO.write(result, image_ext, origine)){
            this.width = result.getWidth();
            this.height = result.getHeight();
        }
    }
    
    /**
     * retourne l image genere
     * 
     * @return File
     * @throws IOException 
     */
    public File get() throws IOException{
        if(!destination.isFile()){
            destination.createNewFile();
            BufferedImage img = ImageIO.read(origine);
            ImageIO.write(img, image_ext, destination);
        }
        
        return destination;
    }
}
