package com.pichy.pokemon.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ManagerSprite {
    public BufferedImage sprite;

    public ManagerSprite(String path) {
        sprite = getImage(path);
    }

    private BufferedImage getImage(String path) {
        try {
            //return ImageIO.read(ManagerSprite.class.getResource(path));
            return ImageIO.read(ManagerSprite.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return sprite.getSubimage(x, y, width, height);
    }
}
