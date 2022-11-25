package com.pichy.pokemon.maker;

import com.pichy.pokemon.block.Block;
import com.pichy.pokemon.block.TypeBlocks;
import com.pichy.pokemon.util.MyUtil;

import java.util.ArrayList;

@Deprecated
public class Maker {
    public String[] info;
    public int largura;
    public int altura;

    public ArrayList<Block> blocks = new ArrayList<>();

    public Maker(String path) {
        try {
            info = MyUtil.readTxt(path).split(",");

            largura = Integer.parseInt(info[0]);
            altura = Integer.parseInt(info[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 2; i < info.length; i++)
            blocks.add(convertStringToBlock(info[i]));
    }

    private Block convertStringToBlock(String str) {
        String[] split = str.split(";");
        String[] subSplit = split[0].split("/");
        int x = Integer.parseInt(subSplit[0]);
        int y = Integer.parseInt(subSplit[1]);
        return new Block(TypeBlocks.valueOf(split[1]), x, y);
    }

}
