package com.pichy.pokemon.maker;

import com.pichy.pokemon.block.Block;
import com.pichy.pokemon.block.TypeBlocks;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Deprecated
public class Paint {

    public int x, y = 0;

    public TypeBlocks brush = TypeBlocks.GRASS;

    public Paint() {

    }

    public void update(int xCamera, int yCamera) {
        x = -xCamera;
        y = -yCamera;
    }

    public void render(Graphics g) {
        g.setColor(new Color(151, 151, 151, 255));
        g.fillRect(x, y, 250, 55);
        g.setColor(new Color(76, 76, 76, 255));
        g.fillRect(x, y, 245, 50);

        g.setColor(new Color(151, 151, 151, 255));
        g.fillRect(x + 545, y + 5, 250, 55);
        g.setColor(new Color(76, 76, 76, 255));
        g.fillRect(x + 545, y + 5, 245, 50);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("x: " + MapMaker.player.x + ", y: " + MapMaker.player.y, x + 5, y + 475);
    }

    public void setPaint(String str) {
        str = str.toUpperCase();
        System.out.println(str);
        for (TypeBlocks t : TypeBlocks.values()) {
            System.out.println("str=" + str + "/" + t.name());
            if (str.equalsIgnoreCase(t.name())) {
                brush = TypeBlocks.valueOf(str);
                System.out.println("valueof=" + TypeBlocks.valueOf(str));
                break;
            }
        }
    }

    public void placeBlock(int x, int y) {
        x = Math.round(x);
        y = Math.round(y);
        MapMaker.world.blocks.add(new Block(brush, x, y));
    }

    public void changeTypeBlock(Block b) {

        b.setType(brush);
        //mais ou menos
    }

    public void addBlockAtBlock(Block b) {

        MapMaker.world.blocks.add(new Block(brush, b.x, b.y));
    }

    public void removeBlock(Block b) {
        int x = 0;

        for (int i = 0; i < MapMaker.world.blocks.size(); i++) {
            if (MapMaker.world.blocks.get(i).x == b.x && MapMaker.world.blocks.get(i).y == b.y)
                x++;
        }
        if (x > 1)
            MapMaker.world.blocks.remove(b);
    }

    public void saveMap() {
        int widht = MapMaker.world.width;
        int height = MapMaker.world.height;

        String out = widht + "," + height + ",";

        for (Block b : MapMaker.world.blocks)
            out = out + convertBlockToString(b);

        Writer writer = null;

        try {
            //  new File("src/resource/mapa.txt").delete();
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("src/resource/mapa.txt"), StandardCharsets.UTF_8));
            writer.write(out.substring(0, out.length() - 1));
        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }

        //  System.out.println(out.substring(0, text.length() - 1););
        System.out.println(MapMaker.world.blocks.size());
    }


    private String convertBlockToString(Block b) {
        int x = b.x;
        int y = b.y;
        String type = b.type.name();
        return x + "/" + y + ";" + type + ",";
    }

    /*
       private Block convertBlockToString(String str){
        String[] split = str.split(";");
        int x = Integer.parseInt(split[0]) * 32;
        int y = 0;
        for(int i = 0, loop = 1; i < loop ; i++){
            if(x>largura){
                y = loop * 32;
                x = Math.abs(x - largura) - 32;
                loop++;
            }
        }
        return new Block(TypeBlocks.valueOf(split[1]), x,y);
    }
     */
}
