class Main 
{
    public static void main(String[] args)
    {
        McNairPoly g = new McNairPoly();
        g.divider();
        
        /* Runs game while no winner */
        while(!g.checkIfWinner())
        {

            g.choices();
            
            if(!g.isPlayerInJail())
            {
                g.move();
                g.action();
                g.nextTurn();
                g.divider();
            }

            else
            {
                g.inJailAction();
                g.nextTurn();
                g.divider();
            }
        }
   }
}
