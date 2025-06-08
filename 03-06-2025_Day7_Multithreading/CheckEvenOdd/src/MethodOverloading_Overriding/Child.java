package MethodOverloading_Overriding;

public class Child extends Parent {
    public void print(){
        System.out.println("I am in child class");
    }

    public int add(int a, int b){
        System.out.println("Adding from child class");
        return a + b;
    }

    public int add(int a, int b, int c){
        System.out.println("Adding from child class ");
        return a + b + c;
    }
}
