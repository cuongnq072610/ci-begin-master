package base.enemy;

import base.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.SingleImgRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider collider;
    int damge;

    public EnemyBullet() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        this.renderer = new SingleImgRenderer(image);
        this.position = new Vector2D(0,0);
        this.collider = new BoxCollider(16,16);
        this.damge = 4;
    }

    @Override
    public void run() {
        Player player = GameObject.intersect(Player.class, this);
        if(player != null) {
            player.takeDamge(this.damge);
            this.destroy();
            return;
        }
        if(this.position.y < 0) {
            this.destroy();
            return;
        }
        this.position.addThis(0,2);
    }



    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }

}
