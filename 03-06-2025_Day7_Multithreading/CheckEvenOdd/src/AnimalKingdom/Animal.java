package AnimalKingdom;

public class Animal extends Species {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   public Animal(String type, String color, int noOfLegs, boolean wings ){
        super(color, noOfLegs, wings);
        this.type = type;
    }
}
