class Parent
{
    public void displayClass()
    {
        System.out.println("Inside super class Parent");
    }
}

class Child1 extends Parent
{
    public void displayClass()
    {
        System.out.println("Inside sub class Child1");
    }
}

class Child2 extends Parent
{
    public void displayClass()
    {
        System.out.println("Inside sub class Child2");
    }
}

class Bound<T extends Parent>
{
    private T objRef;

    public Bound(T obj){
        this.objRef = obj;
    }

    public void doRunTest(){
        this.objRef.displayClass();
    }
}

public class Main
{
    public static void main(String a[])
    {
        Bound<Child2> bec = new Bound<Child2>(new Child2());
        bec.doRunTest();

        Bound<Child1> beb = new Bound<Child1>(new Child1());
        beb.doRunTest();

        Bound<Parent> bea = new Bound<Parent>(new Parent());
        bea.doRunTest();

    }
}