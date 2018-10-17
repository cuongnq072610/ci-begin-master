package base.player;

import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Physics;
import base.Vector2D;
import base.enemy.Enemy;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics {
    // create a new Vector contains velocity of each bullet
    // if want to change direct of bullet, set value to velocity
    // change run, not add to position now add to velocity

    // Vector2D velocity;
    BoxCollider boxCollider;
    int damage;
    public PlayerBullet(){
        super();
        this.position = new Vector2D(0,0);
        //this.velocity = new Vector2D(0,0);
    }

    public void move(int moveY){
        this.position.addThis(0,moveY);
        // this.velocity.addThis()
    }

    public void hitEnemy(){

    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this);
        if(enemy != null){
            enemy.takeDamage(this.damage);
            this.hitEnemy();
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
