package Shapes;

class Shape {

    private String type;
    private int length;
    private int breadth;

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public int getLength() {
        return length;
    }
    public String getType() {
        return type;
    }
    public int getBreadth() {
        return breadth;
    }

    public Shape (){};
    public Shape(String type, int length, int breadth) {
        this.type = type;
        this.length = length;
        this.breadth = breadth;
    }

    int area(){
        int a = length * breadth;
        System.out.println("Area: " + a);
        return a;
    }
    int perimeter(){
        int p = 2 * (length + breadth);
        System.out.println("Perimeter: " + p);
        return p;
    }
}
