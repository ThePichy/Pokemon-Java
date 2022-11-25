package com.pichy.pokemon.movement;

import com.pichy.pokemon.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //up
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87)
            Main.getInstance().getPlayer().moveForward();
        //left
        if (e.getKeyCode() == 37 || e.getKeyCode() == 65)
            Main.getInstance().getPlayer().moveLeft();
        //right
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68)
            Main.getInstance().getPlayer().moveRight();
        //down
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83)
            Main.getInstance().getPlayer().moveBack();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.getInstance().getPlayer().movement = false;
    }
}
