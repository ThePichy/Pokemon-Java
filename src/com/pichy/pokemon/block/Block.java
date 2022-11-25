package com.pichy.pokemon.block;

import java.awt.image.BufferedImage;

public class Block {
    public int x, y;
    public int size;
    public boolean colission;
    public BufferedImage texture;
    public TypeBlocks type;

    public Block(int x, int y, int size, Boolean colission) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.colission = colission;

    }

    public Block(int x, int y, int size, Boolean colission, BufferedImage texture) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.colission = colission;
        this.texture = texture;
    }

    public Block(TypeBlocks type, int x, int y) {
        this.x = x;
        this.y = y;
        setType(type);
    }

    public void setType(TypeBlocks type) {
        this.type = type;
        this.size = type.getSize();
        this.colission = type.getColission();
        this.texture = type.getTexture();
    }
}
