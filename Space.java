public class Space 
{
    private String name;
    private int location;
    private boolean isSpecial;
    
    public Space(String name, int location, boolean isSpecial)
    {
        this.name = name;
        this.location = location;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getLocation() 
    {
        return location;
    }

    public void setLocation(int location) 
    {
        this.location = location;
    }

    public boolean isSpecial() 
    {
        return isSpecial;
    }
    
}
