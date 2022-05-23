public class Card
{

    private String name;
    private int location;
    private boolean isSpecial;

    //A classroom space constructor
    public Card(String name, int loc, boolean isSpecial)
    {
        this.name = name;
        this.location = loc;
        this.isSpecial = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String toString() 
    {
        return "Card [isSpecial=" + isSpecial + ", location=" + location + ", name=" + name + "]";
    }
}
