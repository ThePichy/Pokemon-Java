package com.pichy.pokemon.sound;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MP3 {
    private final String filename;
    private Player player;

    public MP3(String filename) {
        this.filename = filename;
    }

    public void close() {
        if (this.player != null)
            this.player.close();
    }

    public void play() {
        try {
            FileInputStream fis = new FileInputStream(this.filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            this.player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + this.filename);
            System.out.println(e);

        }
        (new Thread() {
            public void run() {
                try {
                    MP3.this.player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
