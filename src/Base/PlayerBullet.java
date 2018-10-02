package Base;

import Base.Renderer.AnimationRenderer;
import Game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject {

    public PlayerBullet(){
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/player-bullets/a/0.png",
                "assets/images/player-bullets/a/1.png",
                "assets/images/player-bullets/a/2.png",
                "assets/images/player-bullets/a/3.png"
        );

        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0,0);
    }

    public void move(int moveY){
        this.position.addThis(0,moveY);
    }

    @Override
    public void run() {
         this.move(-5);
    }

}
