//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package edu.cis.Utils;

import acm.graphics.GImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * Provides Util methods that are useful for changing strings into other media forms.
 * Methods only work with GImage, which is part of the acm graphics package, they don't
 * work for Android Image class.
 */
public class SimpleNetworking
{
    public SimpleNetworking()
    {
    }

    public static String imageToString(GImage image)
    {
        if (image == null)
        {
            return "";
        } else
        {
            try
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(imageToBufferedImage(image.getImage()), "png", baos);
                byte[] bytes = baos.toByteArray();
                return Base64.getUrlEncoder().encodeToString(bytes);
            } catch (IOException excp)
            {
                return null;
            }
        }
    }

    public static GImage stringToImage(String str)
    {
        if (str != null && !str.equals(""))
        {
            try
            {
                byte[] bytes = Base64.getUrlDecoder().decode(str);
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                return new GImage(img);
            } catch (IOException excp)
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    private static BufferedImage imageToBufferedImage(Image im)
    {
        BufferedImage bi = new BufferedImage(im.getWidth((ImageObserver) null), im.getHeight((ImageObserver) null), 1);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, (ImageObserver) null);
        bg.dispose();
        return bi;
    }
}
