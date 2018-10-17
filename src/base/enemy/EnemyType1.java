package base.enemy;

import base.physics.BoxCollider;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyType1 extends Enemy {
    public EnemyType1(){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png"
        );
        this.boxCollider = new BoxCollider(28,28);
        this.renderer = new AnimationRenderer(images,5);
    }

    @Override
    public void takeDamage(int damage) {
        if(damage > 0){
            this.destroy();
        }
    }
}
