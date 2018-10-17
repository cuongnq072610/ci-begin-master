package base.player;

import base.physics.BoxCollider;
import base.renderer.SingleImgRenderer;

public class PlayerBulletType1 extends PlayerBullet {
    public PlayerBulletType1(){
        super();
        this.renderer = new SingleImgRenderer("assets/images/player-bullets/a/0.png");
        this.boxCollider = new BoxCollider(24,24);
        this.damage = 1;
    }

    @Override
    public void hitEnemy() {
        this.destroy();
    }
}
