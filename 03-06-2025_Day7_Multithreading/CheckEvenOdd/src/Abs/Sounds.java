package Abs;

// In Sounds.java
public class Sounds extends Car {

    protected void carSound() {
        System.out.println("car Sound");
    }
    protected void features(){
        System.out.println("Car engine name: V8 ");
    }

    public void callAbstractMethod(){
        carSound();
        features();
        CarName="a";
    }
}
