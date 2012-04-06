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

    BufferedImage img;
    private String image_url;
    private String image_ext;
    private String image_dest;

    public Photo(String image_url, String image_dest) {
        super(ImageIO.read(new File(image_url)));
        this.image_url = image_url;
        String ext[] = image_url.split("\\.");
        this.image_ext = ext[0].toUpperCase();
        this.image_dest = image_dest;
    }

    private BufferedImage resize(int targetWidth, int targetHeight, BufferedImage src) {
        double scaleW = (double) targetWidth / (double) src.getWidth();
        double scaleH = (double) targetHeight / (double) src.getHeight();

        double scale = scaleW < scaleH ? scaleW : scaleH;

        BufferedImage result = new BufferedImage((int) (src.getWidth() * scale),
                (int) (src.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(src, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();

        return result;
    }

    private BufferedImage cropImage(x, y, width, height) {
        BufferedImage dest = img.getSubimage(x, y, width, height);
        return dest;
    }

    public void flush() throws IOException {
        BufferedImage origin = ImageIO.read(new File(image_url));
        File dest = new File(image_dest);
        dest.createNewFile();
        ImageIO.write(resize(100, 100, origin), image_ext, dest);
    }
}
