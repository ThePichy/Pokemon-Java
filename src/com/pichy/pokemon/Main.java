package com.pichy.pokemon;

import com.pichy.pokemon.entity.Camera;
import com.pichy.pokemon.entity.Player;
import com.pichy.pokemon.image.ManagerSprite;
import com.pichy.pokemon.maker.Maker;
import com.pichy.pokemon.movement.KeyboardInput;
import com.pichy.pokemon.sound.MP3;
import com.pichy.pokemon.world.Location;
import com.pichy.pokemon.world.World;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Data
public class Main extends Canvas implements Runnable {

    public static final String NAME = "Pokemon-Java";
    @Getter
    public static Main instance;
    public static int HEIGHT = 480;
    public static int WIDTH = 620;
    public JFrame jframe;

    public Player player;
    public Camera camera;

    public ManagerSprite managerSprite;
    public ManagerSprite managerSpriteBlock;

    public World world;
    //public satic World world2;

    public Font font;
    public Maker maker;
    public MP3 mp3;

    public int fps;
    private long lastTime;

    public Main() {
        instance = this;

        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(dimension);
        addKeyListener(new KeyboardInput());

        // Load mp3

        mp3 = new MP3("data/sounds/bg/b1.mp3");
        mp3.play();

        // Load font

        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("resource/Font.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(12f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load sprites

        managerSpriteBlock = new ManagerSprite("/resource/blocks.png");
        managerSprite = new ManagerSprite("/resource/spritesheet.png");

        // Load player and camera

        camera = new Camera(0, 0);
        player = new Player(0, 0, 4, 14, 13, 32, 32);

        // Load worlds

        maker = new Maker("/resource/mapa.txt");

        world = new World("Mundo", maker.largura, maker.altura);
        world.blocks = maker.blocks;
        world.setSpawn(new Location(world, world.width / 2, world.height / 2));

        player.world = world;
        player.teleport(world.spawn);

    }

    public static void main(String[] args) {
        new Main();

        JFrame jFrame;
        jFrame = new JFrame(NAME);
        jFrame.add(Main.instance);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        Main.instance.setJframe(jFrame);

        (new Thread(instance)).start();
    }

    public void update() {
        player.update();
        camera.update(player.x, player.y);
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
        player.render(g);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Fps: " + fps, player.x - 300, player.y - 220);
        g2.translate(-camera.x, -camera.y);

        bs.show();
    }


    @Override
    public void run() {
        while (true) {
            update();
            render();
            lastTime = System.nanoTime();
            try {
                Thread.sleep((1000 / 60));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fps = (int) (1000000000.0 / (System.nanoTime() - lastTime)); //one second(nano) divided by amount of time it takes for one frame to finish
            lastTime = System.nanoTime();
        }
    }

}