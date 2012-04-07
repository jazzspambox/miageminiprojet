/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Photo {

    /**
     * 
     */
    BufferedImage img;
    File destination;
    private String image_ext;

    /**
     * 
     * @param image_url
     * @param image_dest
     * @throws IOException 
     */
    private Photo(String dir, String origine, String image_name) throws IOException {   
        img = ImageIO.read(new File(dir+origine));
        String ext[] = origine.split("\\.");
        this.image_ext = ext.length>0? ext[1].toLowerCase() : "";
        this.destination = new File(dir+image_name+'.'+this.image_ext);
    }

    /**
     * 
     * @param targetWidth
     * @param targetHeight
     * @throws IOException 
     */
    public void resize(int targetWidth, int targetHeight) throws IOException {
        double scaleW = (double) targetWidth / (double) img.getWidth();
        double scaleH = (double) targetHeight / (double) img.getHeight();
        double scale = scaleW < scaleH ? scaleW : scaleH;

        BufferedImage result = new BufferedImage((int) (img.getWidth() * scale),
                (int) (img.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(img, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();
        
        destination.createNewFile();
        ImageIO.write(result, image_ext, destination);
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void cropImage(int x, int y, int width, int height) {
        img = img.getSubimage(x, y, width, height);
    }
}
