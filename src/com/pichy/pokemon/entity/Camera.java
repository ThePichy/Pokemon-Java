package com.pichy.pokemon.entity;

import com.pichy.pokemon.Main;

public class Camera {
    public int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(int x, int y) {
        this.x = -x + Main.WIDTH / 2;
        this.y = -y + Main.HEIGHT / 2;
    }

}
