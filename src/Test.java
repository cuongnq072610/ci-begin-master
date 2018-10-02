public class Test {
    // thuoc tinh
    public int count;

    //constructor
    public Test(){
        this.count = 0;
    }

    public Test(int count){
        this.count = count;
    }


    // method1
    public void plus(int x){
        count += x;
    }

    public void print(){
        System.out.println(count);
    }
}
