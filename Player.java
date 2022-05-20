import java.util.ArrayList;

public class Player 
{
    private String name;
    private int pos;
    private int GPA;
    private boolean isInGame;
    private ArrayList<Property> propertiesOwned;

    private boolean inJail;
    private int daysInJail;

    public Player(String name)
    {
        this.name = name;
        pos = 0;
        GPA = 100;
        isInGame = true;
        propertiesOwned = new ArrayList<Property>();
        inJail = false;
        daysInJail = 0;
    }

    public void bankrupt(Player obj)
    {
        isInGame = false;

        obj.addGPA(GPA);
        for(Property prop : propertiesOwned)
        {
            prop.setOwner(obj);
            obj.addProperty(prop);
        }
    }
    
    public void passGo()
    {
        GPA += 25;
        System.out.println("\n[GO] You passed go and collected 25 GPA");
    }

    public void buy(Property prop)
    {
        propertiesOwned.add(prop);
        prop.setOwner(this);
        prop.setOwned(true);
    }

    public void payRent(Property prop)
    {
        Player propOwner = prop.getOwner();
        propOwner.addGPA(prop.getCost());
        this.GPA -= prop.getCost();
    }
    
    public void payTax(Property prop)
    {
        GPA -= prop.getTax();
    }
    
    public void putInDetention()
    {
       if(!inJail)
       {
            inJail = true;
       }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getGPA() {
        return GPA;
    }

    public void addGPA(int GPA) {
        this.GPA += GPA;
    }

    public boolean getIsInGame() {
        return isInGame;
    }

    public void setInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public ArrayList<Property> getPropertiesOwned() 
    {
        return propertiesOwned;
    }

    public void addProperty(Property prop) 
    {
        propertiesOwned.add(prop);
    }

    public boolean isInJail() 
    {
        return inJail;
    }

    public void setInJail(boolean inJail) 
    {
        this.inJail = inJail;
    }

    public int getDaysInJail() 
    {
        return daysInJail;
    }

    public void setDaysInJail(int daysInJail) 
    {
        this.daysInJail = daysInJail;
    }

    @Override
    public String toString() 
    {
        if(isInGame)
        {
            return "Player [GPA=" + GPA + ", isInGame=" + isInGame + ", name=" + name + ", pos=" + pos + ", Properties: " + propertiesOwned + "]";
        }
        else
        {
            return name + " has gone bankrupt :(";
        }
    }

    
}
