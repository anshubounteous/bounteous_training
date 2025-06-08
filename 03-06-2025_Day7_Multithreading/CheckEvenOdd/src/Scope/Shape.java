package Scope;

public class Shape {
    public int l;
    public int b;

    public Shape() {}
    public Shape(int l, int b) {
        this.l = l;
        this.b = b;
    }

    public void area() {
        int extraArea = 100;
        System.out.println("Area : " + (l * b + extraArea));
    }
}
