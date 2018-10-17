package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
// game Object Pool:
// - An array contains all game objects in game
// - Recycle game objects
public class GameObject {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObjs = new ArrayList<>();
    public static BufferedImage backBuffer = new BufferedImage(Setting.SCREEN_WIDTH,Setting.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
    public static Graphics backBufferGraphics = backBuffer.createGraphics();

    public static <E extends GameObject> /*<- Kieu Class*/ E /*<- Kieu tra ve*/ create(Class<E> childClass){
        try {
            GameObject newGameObj = childClass.newInstance();
            newGameObjs.add(newGameObj);
            return (E) newGameObj;
        } catch(Exception e){
            return null;
        }
    }

    public static <E extends GameObject> E recycle(Class<E> childClass){
        //1. Kiem tra co game object thoa man yeu cau: isActive = false && gameobject dung la class con khoi tao
        // co thi dung lai
        // k co thi tao moi
        //2. Return gameObject
        for (GameObject gameObject: gameObjects) {
            if(!gameObject.isActive && gameObject.getClass().isAssignableFrom(childClass)){
                gameObject.isActive = true;
                return (E) gameObject;
            }
        }
        return create(childClass);
    }

    public static <E extends GameObject> E intersect (Class<E> childClass, Physics physics){
        for (GameObject go :
                gameObjects) {
            if (go.isActive && childClass.isAssignableFrom(go.getClass()) && go instanceof Physics){
                Physics physicsGo = (Physics) go;
                boolean intersected = physics.getBoxCollider().intersect(physicsGo, (GameObject) physics);
                if(intersected){
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }

    public static void runAll(){
        for (GameObject go : gameObjects) {
            if(go.isActive) {
                go.run();
            }
        }
        gameObjects.addAll(newGameObjs);
        newGameObjs.clear();
    }

    public static void renderAllToBackBuffer(){
        for (GameObject go :
                gameObjects) {
            if (go.isActive){
                go.render(backBufferGraphics);
            }
        }
    }

    public static void renderBackBufferToGame(Graphics g){
        g.drawImage(backBuffer,0,0,null);
    }

    public Renderer renderer;
    public Vector2D position;
    public boolean isActive;
    public Vector2D anchor;

    public GameObject() {
        this.isActive = true;
        this.anchor = new Vector2D(0.5f,0.5f);
    }


    public void destroy(){
        this.isActive = false;
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
