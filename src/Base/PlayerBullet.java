package Base;

import Base.Renderer.AnimationRenderer;
import Game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics{
    // create a new Vector contains velocity of each bullet
    // if want to change direct of bullet, set value to velocity
    // change run, not add to position now add to velocity

    // Vector2D velocity;
    BoxCollider boxCollider;
    public PlayerBullet(){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/player-bullets/a/0.png",
                "assets/images/player-bullets/a/1.png",
                "assets/images/player-bullets/a/2.png",
                "assets/images/player-bullets/a/3.png"
        );
        this.boxCollider = new BoxCollider(24,24);
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0,0);
        //this.velocity = new Vector2D(0,0);
    }

    public void move(int moveY){
        this.position.addThis(0,moveY);
        // this.velocity.addThis()
    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this);
        if(enemy != null){
            enemy.destroy();
            this.destroy();
            return;
        }
        if(this.position.y < 0){
            this.destroy();
            return;
        }
        this.move(-5);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
