class Main 
{
    public static void main(String[] args)
    {
        McNairPoly g = new McNairPoly(); //make object
       
        g.divider(); // prints the dashes within the program (in console)
        
        //Runs game while no winner
        while(!g.checkIfWinner()) //Change
        {

            g.choices(); 
          
            if(!g.isPlayerInJail() && !g.isGameOver()) //Change
            {
                g.move(); //moves the player
                g.action(); //allows the player to choose
                g.nextTurn(); // gives the next player a turn
                g.divider(); // creates more dashes. 
            }

            else if(g.isPlayerInJail() && !g.isGameOver()) //Change
            {
                g.inJailAction();
                g.nextTurn();
                g.divider(); //Change
            }

            else //Change
            {
              break;
            }
        }

      g.divider();
      g.divider();
      System.out.println("\nGame Over!"); //Change
   }
}
