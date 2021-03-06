package base.player;

import base.*;
import base.counter.FrameCounter;
import base.event.KeyEventPress;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    FrameCounter fireCounter;
    BoxCollider collider;
    public int hp;
    public Vector2D velocity;


    public Player (){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/players/straight/0.png",
                "assets/images/players/straight/1.png",
                "assets/images/players/straight/2.png",
                "assets/images/players/straight/3.png",
                "assets/images/players/straight/4.png",
                "assets/images/players/straight/5.png",
                "assets/images/players/straight/6.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(Setting.START_PLAYER_POSITION_X,Setting.START_PLAYER_POSITION_Y);
//        Setting.newX = this.position.x;
//        Setting.newY = this.position.y;
        this.fireCounter = new FrameCounter(10);
        this.collider = new BoxCollider(32,48);
        this.hp = 20;
        this.velocity = new Vector2D(0,0);
    }

    public void move(int velocityX, int velocityY){
        this.velocity.addThis(velocityX,velocityY);
        this.velocity.set(clamp(velocity.x,-3,3), clamp(velocity.y,-3,3));
    }

    public float clamp(float number, float min, float max){
//        if(number<min){
//            return min;
//        } else if(number>max){
//            return max;
//        } else return number;

        return number < min ? min : number > max ? max : number;
    }

    @Override
    public void run() {
        if(KeyEventPress.isUpPress){
            this.move(0,-1);
            System.out.println(this.velocity.x + " " + this.velocity.y);
        }
        if(KeyEventPress.isDownPress){

            this.move(0,1);
        }
        if(KeyEventPress.isRightPress){

            this.move(1,0);

        }
        if(KeyEventPress.isLeftPress){
            this.move(-1,0);

        }
        //fire
        boolean fireCounterRun = this.fireCounter.run();
        if (KeyEventPress.isSpacePress && fireCounterRun){
            this.fire();
        }
        this.position.addThis(this.velocity);
    }

    public void fire(){
        //PlayerBullet bullet = new PlayerBullet();
        PlayerBulletType1 bullet = GameObject.recycle(PlayerBulletType1.class);
        ExtraBullet extraBullet = GameObject.recycle(ExtraBullet.class);
        ExtraBullet2 extraBullet2 = GameObject.recycle(ExtraBullet2.class);

        bullet.position.set(this.position.x, this.position.y);
        extraBullet.position.set(this.position.x-1, this.position.y-1);
        extraBullet2.position.set(this.position.x+1, this.position.y-1);

        //GameCanvas.bullets.add(bullet);
        this.fireCounter.reset();
            // count 10 frame set = true
    }

    public void takeDamge(int damge){
        this.hp -= damge;
        System.out.println(this.hp);
        if(this.hp <= 0){
            this.destroy();
            this.hp = 0;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
