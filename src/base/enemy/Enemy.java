package base.enemy;

import base.*;
import base.action.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    BoxCollider boxCollider;
    Action action;

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
        this.defineAction();

    }

    void defineAction(){
        ActionWait actionWait = new ActionWait(20);
        Action actionFire =  new Action() {
            @Override
            public void run(GameObject master) {
                fire();
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionMove = new Action() {
            @Override
            public void run(GameObject master) {
                move();
            }

            @Override
            public void reset() {

            }
        };

        ActionSequence actionSequence = new ActionSequence(actionWait, actionFire);
        ActionParallel actionParallel = new ActionParallel(actionMove,actionSequence);
        ActionRepeat actionRepeat = new ActionRepeat(actionParallel);
        this.action = actionRepeat;
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

    public void fire() {
        EnemyBullet bullet1 = GameObject.recycle(EnemyBullet.class);
        bullet1.position.set(this.position.x, this.position.y+5);
    }


    @Override
    public void run() {
        this.action.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
