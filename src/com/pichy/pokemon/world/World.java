package com.pichy.pokemon.world;

import com.pichy.pokemon.block.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class World {

    public String name;
    public int width, height;

    public ArrayList<Block> blocks = new ArrayList<>();
    public Location spawn;

    public int chunk = 8;

    public World(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public void render(int x, int y, Graphics2D g) {
        renderBlocks(x, y, g);
    }

    private void renderBlocks(int x, int y, Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, width * 2, height * 2);

        for (final Iterator<Block> iterator = blocks.iterator(); iterator.hasNext(); ) {
            Block b = iterator.next();

            if (b.x < x + 32 * (chunk + 4) && b.x + b.size * (chunk + 4) > x && b.y < y + 32 * (chunk + 2) && b.y + b.size * (chunk + 2) > y)
                g.drawImage(b.texture, b.x, b.y, b.size, b.size, null);

        }

    }

}
