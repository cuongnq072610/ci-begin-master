package Game;

import Base.*;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {
    BackGround backGround;
    Player player;

    public GameCanvas(){// constructor
        this.backGround = GameObject.recycle(BackGround.class);
        this.player =  GameObject.recycle(Player.class);
        Enemy enemy = GameObject.recycle(Enemy.class);
    }


    public void run(){// call all run function from all class object
        GameObject.runAll();
    }

    public void render(Graphics graphics){// call all render function from all class object
        GameObject.renderAll(graphics);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        this.render(graphics);
    }
}
