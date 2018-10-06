package Base;

import Base.Renderer.SingleImgRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider collider;

    public EnemyBullet() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        this.renderer = new SingleImgRenderer(image);
        this.position = new Vector2D(0,0);
        this.collider = new BoxCollider(14,14);
    }

    @Override
    public void run() {
        Player player = GameObject.intersect(Player.class, this);
        if(player != null) {
            player.destroy();
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
