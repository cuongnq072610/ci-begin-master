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
        if(this.isActive){
            EnemyBullet bullet1 = GameObject.recycle(EnemyBullet.class);
            bullet1.position.set(this.position.x, this.position.y+5);
        }
    }

    public void takeDamage(int damage){

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
