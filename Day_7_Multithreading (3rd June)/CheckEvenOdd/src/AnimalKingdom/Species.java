package AnimalKingdom;

public class Species {
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNoOfLegs() {
        return noOfLegs;
    }

    public void setNoOfLegs(int noOfLegs) {
        this.noOfLegs = noOfLegs;
    }

    public boolean isWings() {
        return wings;
    }

    public void setWings(boolean wings) {
        this.wings = wings;
    }

    String color;
    int noOfLegs;
    boolean wings;

    public Species (String color, int noOfLegs, boolean wings){
        this.color = color;
        this.noOfLegs = noOfLegs;
        this.wings = wings;
    }

}

