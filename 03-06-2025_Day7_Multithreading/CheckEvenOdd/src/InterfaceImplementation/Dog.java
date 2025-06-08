package InterfaceImplementation;

public class Dog implements IAnimal{
    public void sound(int x ,int y ){
        System.out.println("Dog : Bark");
    }
    public void eat(int x,int y){
        System.out.println("Dog : Bone");
    }

    public void someMethod(){
        System.out.println(IAnimal.x);
    }
}
