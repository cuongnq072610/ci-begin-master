package game;

import base.*;
import base.enemy.Enemy;
import base.enemy.EnemyType1;
import base.player.Player;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    BackGround backGround;
    Player player;

    public GameCanvas(){// constructor
        this.backGround = GameObject.recycle(BackGround.class);
        this.player =  GameObject.recycle(Player.class);
        EnemyType1 enemy = GameObject.recycle(EnemyType1.class);
    }


    public void run(){// call all run function from all class object
        GameObject.runAll();
    }

    public void render(){// call all render function from all class object
        GameObject.renderAllToBackBuffer();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        GameObject.renderBackBufferToGame(graphics);
    }
}
