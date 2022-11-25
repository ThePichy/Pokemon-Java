package com.pichy.pokemon.maker;

import com.pichy.pokemon.block.Block;
import com.pichy.pokemon.entity.Camera;
import com.pichy.pokemon.image.ManagerSprite;
import com.pichy.pokemon.world.Location;
import com.pichy.pokemon.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

@Deprecated
public class MapMaker extends Canvas implements Runnable, MouseListener, KeyListener {

    public static int altura = 480;
    public static int largura = 620;

    public static JFrame jframe;
    public static MapMaker mapMaker;
    public static Camera camera;
    public static PlayerMapMaker player;

    public static ManagerSprite managerSpriteBlock;
    public static ManagerSprite managerSprite;

    public static World world;
    public static Maker maker;
    public static Paint paint;
    public static Boolean whoColission = false;
    public static Color color_colission = new Color(255, 0, 0, 50);
    public static Color color_NoColission = new Color(55, 255, 0, 50);
    public GridsCanvas grid;

    public MapMaker() {
        Dimension dimension = new Dimension(largura, altura);
        setPreferredSize(dimension);
        addMouseListener(this);
        addKeyListener(this);


        managerSpriteBlock = new ManagerSprite("/resource/blocks.png");
        managerSprite = new ManagerSprite("/resource/spritesheet.png");

        // Main.getInstance().getManagerSpriteBlock() = managerSpriteBlock;
        maker = new Maker("/resource/mapa.txt");
        // grid = new GridsCanvas(maker.largura, maker.altura, 15, 19);
        grid = new GridsCanvas(maker.largura, maker.altura, maker.largura / 32, maker.altura / 32);
        camera = new Camera(0, 0);
        player = new PlayerMapMaker(0, 0, 10, 14, 13, 32, 32);


        System.out.println("L=" + maker.largura + ",H=" + maker.altura);

        world = new World("MapMaker", maker.largura, maker.altura);
        world.setSpawn(new Location(world, maker.largura / 2, maker.altura / 2));

        world.blocks = maker.blocks;

        paint = new Paint();

        player.world = world;
        player.teleport(world.spawn);


    }

    public static void main(String[] args) {
        mapMaker = new MapMaker();
        jframe = new JFrame("MapMaker");

        JTextField field = new JTextField(10);
        field.setBounds(5, 5, 200, 30);

        field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                paint.setPaint(field.getText());
                // .... do some operation on value ...
            }
        });

        JButton jButton = new JButton("Salvar");
        jButton.setBounds(545, 5, 70, 50);
        jButton.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                paint.saveMap();
            }
        });
//
        JButton j = new JButton("Colission");
        j.setBounds(545, 60, 70, 50);
        j.setVisible(true);
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whoColission = !whoColission;
                System.out.println();
            }
        });


        jframe.add(j);
        jframe.add(jButton);
        jframe.add(field);

        jframe.add(mapMaker);


        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setDefaultCloseOperation(3);
        jframe.setVisible(true);
        (new Thread(mapMaker)).start();
    }

    public void update() {
        player.update();
        camera.update(player.x, player.y);
        paint.update(camera.x, camera.y);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.translate(camera.x, camera.y);

        player.world.render(player.x, player.y, g2);
        if (whoColission == true) {
            for (Block b : world.blocks) {
                if (b.colission) {
                    g.setColor(color_colission);
                    g.fillRect(b.x, b.y, b.size, b.size);
                } else {
                    g.setColor(color_NoColission);
                    g.fillRect(b.x, b.y, b.size, b.size);
                }
            }
        }


        g.setColor(Color.CYAN);
        g.fillRect(player.x, player.y, 32, 32);


        player.render(g);

        grid.paint(g);
        paint.render(g);

        g2.translate(-camera.x, -camera.y);

        bs.show();
    }

    @Override
    public void run() {
        while (true) {
            update();
            render();
            try {
                Thread.sleep((1000 / 60));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Block b = findBlockAt(e.getX(), e.getY());
        if (e.getButton() == 1) {
            if (b == null) {

            } else {
                paint.changeTypeBlock(b);
            }
        }
        if (e.getButton() == 3)
            paint.removeBlock(b);
        if (e.getButton() == 2 && b != null)
            paint.addBlockAtBlock(b);
        // remove(e.getX(),e.getY());
    }

    private Block findBlockAt(int x, int y) {
        x = x - camera.x;
        y = y - camera.y;
        for (Block b : world.blocks) {
            if (x >= b.x &&
                    x < b.x + b.size &&
                    y >= b.y &&
                    y < b.y + b.size) {
                return b;
            }
        }
        return null;
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //up

        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
            player.moveFrente();

        }

        //left
        if (e.getKeyCode() == 37 || e.getKeyCode() == 65)
            player.moveEsquerda();

        //right
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68)
            player.moveDireita();

        //down
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
            player.moveAtras();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
