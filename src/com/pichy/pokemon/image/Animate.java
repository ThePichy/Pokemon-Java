package com.pichy.pokemon.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animate {
    private final int count_frames;
    public int loop;
    public int delay;
    public int frameNow = 0;
    public BufferedImage[] frames;

    public BufferedImage image;

    public Animate(int delay, BufferedImage... frames) {
        this.frames = new BufferedImage[frames.length];
        System.arraycopy(frames, 0, this.frames, 0, frames.length);
        this.delay = delay;

        image = frames[0];
        count_frames = frames.length;
    }

    public void update() {
        loop++;
        if (loop >= delay) {
            loop = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        for (int x = 0; x < count_frames; x++) {
            if (frameNow == x)
                image = frames[x];
        }

        frameNow++;

        if (frameNow > count_frames)
            frameNow = 0;
    }

    public void drawAnimate(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

}
