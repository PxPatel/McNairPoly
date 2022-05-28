public class Teleporter extends Card
{
    //howFar decides the range of how far the teleportation can possibly be
    private int howFar;
  
    public Teleporter(int loc, int howFar)
    {
      super("//Took Wrong Stairwell\\\\", loc, false, false, true, false);

      this.howFar = howFar;
    }

    public int teleport()
    {
      int currentLoc = super.getLocation();
      int newLoc = super.getLocation();

      while(newLoc == currentLoc)
      {
          newLoc = (int)((Math.random() * 5 + 1) *howFar) ;
      }
        
      return newLoc;
    }

    public int getHowFar()
    {
      return howFar;
    }

    public void setHowFar(int howFar)
    {
      this.howFar = howFar;
    }

    @Override
    public String toString()
    {
      return super.getName();
    }
}
