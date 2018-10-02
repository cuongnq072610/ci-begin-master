package Base;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2D set(float x, float y){
        this.x = x;
        this.y = y;
        return new Vector2D(x,y);
    }

    // return another vector with the same x, y
    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    }
    // return new vector = old vector + new x, y
    public Vector2D add(float x, float y){
        this.x += x;
        this.y += y;
        return new Vector2D(this.x,this.y);
    }
    // return old vector with new x , y
    public Vector2D addThis(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D subtract(float x, float y){
        Vector2D result = new Vector2D(this.x - x, this.y - y);
        return result;
    }

    public Vector2D subtractThis(float x, float y){
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2D scale(float n){
        Vector2D result = new Vector2D(this.x * n, this.y * n);
        return result;
    }

    public Vector2D scaleThis(float n){
        this.x = this.x * n;
        this.y = this.y * n;
        return this;
    }

    public float length(float x, float y){
        float length = (float)Math.sqrt(x*x + y*y);
        return length;
    }



    public void print(){
        System.out.println(x + " " + y);
    }

//    public static void main(String[] args) {
//        Vector2D v1 = new Vector2D(10,20);
//        v1.print();
//    }
}
