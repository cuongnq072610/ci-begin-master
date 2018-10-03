package Base;

import Base.Renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics{
    BoxCollider boxCollider;
    public Enemy(){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png"
        );
        this.boxCollider = new BoxCollider(28,28);
        this.renderer = new AnimationRenderer(images,5);
        this.position = new Vector2D(new Random().nextInt(Setting.START_PLAYER_POSITION_X),new Random().nextInt(Setting.START_PLAYER_POSITION_Y));
    }

    public void move(){
        if(this.position.x == 350){
            Setting.ENEMY_MOVE = -1;
        }
        else if(this.position.x == 0){
            Setting.ENEMY_MOVE = 1;
        }
        this.position.addThis(Setting.ENEMY_MOVE,0);
        //System.out.println(Setting.ENEMY_MOVE + " " + this.position.x);
    }


    @Override
    public void run() {
        this.move();
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
