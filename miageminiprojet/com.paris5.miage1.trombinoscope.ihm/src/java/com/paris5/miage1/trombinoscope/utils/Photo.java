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
    
    private int width;
    private int height;
    
    /**
     * 
     */
    File origine;
    File destination;
    private String image_ext;

    /**
     * 
     * @param image_url
     * @param image_dest
     * @throws IOException 
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
     * 
     * @param targetWidth
     * @param targetHeight
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void crop(int x, int y, int x2, int y2) throws IOException {
        BufferedImage img = ImageIO.read(origine);
        BufferedImage result = img.getSubimage(x, y, x2, y2);
        if(ImageIO.write(result, image_ext, origine)){
            this.width = result.getWidth();
            this.height = result.getHeight();
        }
    }
    
    public File get() throws IOException{
        if(!destination.isFile()){
            destination.createNewFile();
            BufferedImage img = ImageIO.read(origine);
            ImageIO.write(img, image_ext, destination);
        }
        
        return destination;
    }
}
