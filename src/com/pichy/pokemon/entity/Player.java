package com.pichy.pokemon.entity;

import com.pichy.pokemon.Main;
import com.pichy.pokemon.block.Block;
import com.pichy.pokemon.image.Animate;
import com.pichy.pokemon.movement.Direction;
import com.pichy.pokemon.world.Location;
import com.pichy.pokemon.world.World;

import java.awt.*;

public class Player {
    public int x;
    public int y;
    public int weight;
    public int height;
    public Direction direction = Direction.BACK;
    public int speed;
    public boolean movement;
    public World world;
    /**
     * Animações de Sprites disponiveis
     *
     * @return Animate
     */

    public Animate frente = new Animate(20,
            Main.getInstance().getManagerSprite().getSprite(14, 206, 34, 50),
            Main.getInstance().getManagerSprite().getSprite(78, 204, 34, 50),
            Main.getInstance().getManagerSprite().getSprite(142, 206, 34, 46),
            Main.getInstance().getManagerSprite().getSprite(206, 204, 34, 50));
    public Animate atras = new Animate(20,
            Main.getInstance().getManagerSprite().getSprite(14, 13, 33, 46),
            Main.getInstance().getManagerSprite().getSprite(78, 10, 34, 52),
            Main.getInstance().getManagerSprite().getSprite(142, 12, 34, 48),
            Main.getInstance().getManagerSprite().getSprite(206, 10, 34, 52)
    );
    public Animate esquerda = new Animate(20,
            Main.getInstance().getManagerSprite().getSprite(14, 78, 33, 46),
            Main.getInstance().getManagerSprite().getSprite(80, 76, 32, 48),
            Main.getInstance().getManagerSprite().getSprite(144, 78, 32, 46),
            Main.getInstance().getManagerSprite().getSprite(208, 76, 32, 46));
    public Animate direita = new Animate(20,
            Main.getInstance().getManagerSprite().getSprite(14, 142, 33, 46),
            Main.getInstance().getManagerSprite().getSprite(80, 140, 32, 48),
            Main.getInstance().getManagerSprite().getSprite(144, 142, 32, 46),
            Main.getInstance().getManagerSprite().getSprite(208, 140, 32, 46)
    );

    /**
     * Paramento de contrução para o objeto Player
     *
     * @return void
     */

    public Player(int x, int y, int speed, int sx, int sy, int weight, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Atualização por tick do objeto Player
     *
     * @return void
     */

    public void update() {
        if (direction == Direction.FORWARD) {
            if (movement)
                frente.update();
            else
                frente.image = frente.frames[0];
        } else if (direction == Direction.BACK) {
            if (movement)
                atras.update();
            else
                atras.image = atras.frames[0];
        } else if (direction == Direction.LEFT) {
            if (movement)
                esquerda.update();
            else
                esquerda.image = esquerda.frames[0];
        } else if (direction == Direction.RIGHT) {
            if (movement)
                direita.update();
            else
                direita.image = direita.frames[0];
        }
    }

    /**
     * Renderização do objeto Player
     *
     * @return void
     */

    public void render(Graphics g) {

        if (direction == Direction.FORWARD)
            frente.drawAnimate(g, x, y, weight, height);
        else if (direction == Direction.BACK)
            atras.drawAnimate(g, x, y, weight, height);
        else if (direction == Direction.LEFT)
            esquerda.drawAnimate(g, x, y, weight, height);
        else if (direction == Direction.RIGHT)
            direita.drawAnimate(g, x, y, weight, height);
    }

    /**
     * Movimentos
     *
     * @return void
     */

    public void teleport(Location location) {
        x = location.x;
        y = location.y;
        world = location.world;
    }

    public Boolean canWalk(int x, int y) {
        for (Block b : Main.getInstance().getWorld().blocks)
            if (b.colission && x < b.x + b.size && x + weight > b.x && y < b.y + b.size && y + height > b.y)
                return false;
        return true;
    }

    public void moveForward() {
        direction = Direction.FORWARD;
        for (int i = 1; i < speed; i++)
            if (!canWalk(x, y - i))
                return;
        y = y - speed;
        movement = true;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
        for (int i = 1; i < speed; i++)
            if (!canWalk(x - i, y))
                return;
        x = x - speed;
        movement = true;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
        for (int i = 1; i < speed; i++)
            if (!canWalk(x + i, y))
                return;
        x = x + speed;
        movement = true;
    }

    public void moveBack() {
        direction = Direction.BACK;
        for (int i = 1; i < speed; i++)
            if (!canWalk(x, y + i))
                return;
        y = y + speed;
        movement = true;
    }

}
