package Base;

import Base.Renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObjs = new ArrayList<>();

    public static <E extends GameObject> /*<- Kieu Class*/ E /*<- Kieu tra ve*/ create(Class<E> childClass){
        try {
            GameObject newGameObj = childClass.newInstance();
            newGameObjs.add(newGameObj);
            return (E) newGameObj;
        } catch(Exception e){
            return null;
        }
    }

    public static void runAll(){
        for (GameObject gameObj :
                gameObjects) {
            gameObj.run();
        }
    }

    public static void renderAll(Graphics g){
        for (GameObject gameObj :
                gameObjects) {
            gameObj.render(g);
        }
        gameObjects.addAll(newGameObjs);
        newGameObjs.clear();
    }

    Renderer renderer;
    public Vector2D position;

    public GameObject() {
    }

    public GameObject(BufferedImage image) {
        this.position = new Vector2D(0,0);
    }

    //logic()
    public void run(){

    }

    //render()
    public void render(Graphics g){
        if(this.renderer!=null){
            this.renderer.Render(g,this);
        }
    }
}
