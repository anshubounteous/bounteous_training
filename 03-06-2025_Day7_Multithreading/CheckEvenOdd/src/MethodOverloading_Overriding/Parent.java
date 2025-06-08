package MethodOverloading_Overriding;

public class Parent {

   public void print(){
        System.out.println("I am in class Parent class ");
    }

    public int add(int a, int b){
        System.out.println("Adding from Parent class ");
       return a + b;
    }
    public int add(int a, int b, int c){
        System.out.println("Adding from Parent class");
        return a + b + c;
    }
}
