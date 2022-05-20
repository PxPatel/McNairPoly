import java.util.Scanner;


public class McNairPoly
{
    private Player[] players;
    private int numUsers;
    private int turn;
    private Player playerAtTurn;

    private Scanner scan = new Scanner(System.in);
    
    private Property[] board;
    private final int BOARD_SIZE = 20; //6 x 6 board

    private final int oneSecond = 1000; 
    private final int halfSecond = 500;

    public McNairPoly()
    {
        turn = 0;

        int num = 100;
        while(num < 0 || num > 6)
        {
            System.out.print("How many people are playing (1-6)? ");
            num = scan.nextInt();
        }

        //To consume the empty line and not cause errors regarding the Scanner class
        scan.nextLine();

        players = new Player[num];
        numUsers = num;
         // Asks for player names and assigns them
		for (int i = 0; i < numUsers; i++) 
        {
			System.out.print("What is player " + (i + 1) + "'s name? ");
			String name = scan.nextLine();
			players[i] = new Player(name);
		}

        //Initalizes the array and creates a Property objects
        initializeBoard();

        playerAtTurn = players[0];
    }

    public void initializeBoard()
    {
        board = new Property[BOARD_SIZE];

        board[0] = new Property("Go", 0, false, false, false, true);
        board[1] = new Property("Gym", 1, 5, 0);
        board[2] = new Property("Mythology", 2, 7, 0);
        board[3] = new Property("Small Tax - Forgot HW", 3, true, false, false, false);
        board[4] = new Property("French 1", 4, 10, 0);
        board[5] = new Property("Sexually Harrased", 5, false, false, true, false);

        board[6] = new Property("Driver\'s Ed", 6, 13, 0);
        board[7] = new Property("Lit 3", 7, 16, 0);
        board[8] = new Property("Chem Honors", 8, 18, 0);
        board[9] = new Property("AP Environmental Science", 8, 20, 0);

        board[10] = new Property("Detention", 9, false, false, true, false);
        board[11] = new Property("AP Lit", 10, 30, 0);
        board[12] = new Property("Small Tax - Forgot Project", 11, true, false, false, false);
        board[13] = new Property("AP USH", 12, 35, 0);
        board[14] = new Property("AP Calc AB", 13, 37, 0);
        board[15] = new Property("Double Lunch", 14, false, true, false, false);
        
        board[16] = new Property("Small Tax - Late to Class", 16, true, false, false, false);
        board[17] = new Property("AP Physics 1", 17, 56, 0);
        board[18] = new Property("AP Chem", 18, 64, 0);
        board[19] = new Property("AP Calc BC", 19, 70, 0); 
    }

    private void sleep()
    {
        try
        {
            Thread.sleep(oneSecond);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private int[] roll()
    {
        int a = (int)(Math.random() * 6) + 1;
        int b = (int)(Math.random() * 6) + 1;
        int[] total = {a,b};
        System.out.println("[Rolling...]");

        sleep();

        System.out.println(playerAtTurn.getName() + " rolled a [" + a + "] [" + b + "]");

        return total;
    }

    public void move()
    {
        System.out.println("\n[New Turn] " + playerAtTurn.getName() + ", would you like to roll? [ENTER TO CONTINUE]");
        scan.nextLine();

        int[] dice = roll();
        int roll = dice[0] + dice[1];

        int currPos = playerAtTurn.getPos();
        int newPos = (currPos + roll) % board.length;
        playerAtTurn.setPos(newPos);

        if(currPos > newPos)
        {
            playerAtTurn.passGo();
        }

        System.out.println("\n" + playerAtTurn.getName() + " landed on " + board[newPos]);
    }

    public boolean isPlayerInJail()
    {
        if(playerAtTurn.isInJail())
        {
            return true;
        }
        return false;
    }

    public void action()
    {
        Player mainPlayer = players[turn]; 
        Property landed = board[mainPlayer.getPos()];
        if(landed.isSpecial())
        {
       
            if(landed.isTax())
            {
                mainPlayer.payTax();
            }
            // else if(landed.isDoubleLunch)
            // {
            //     mainPlayer.inDoubleLunch();
            // }
            else if(landed.isDetention())
            {
                mainPlayer.putInDetention();
            }
            
        }
        else if(!landed.isOwned())
        {
            System.out.print("Would you like to buy " + landed.getName() + "? (Y/N) ");
            String choice = scan.nextLine().toUpperCase();
            if(choice.equals("Y") && playerAtTurn.getGPA() >= landed.getCost())
            {
                players[turn].buy(landed);
                System.out.println(mainPlayer.getName() + " just bought " + landed.getName() + "!");
            }
            else if(choice.equals("Y"))
            {
                System.out.println("Sorry, you don't have enough GPA to buy " + landed.getName());
            }
        }
        else if(landed.isOwned())
        {
            if(players[turn].getGPA() >= landed.getRent())
            {
                players[turn].payRent(landed);
                System.out.println(mainPlayer.getName() + " has payed $" + landed.getRent() + " to " + landed.getOwner().getName());
            }
            else
            {
                players[turn].bankrupt(landed.getOwner());
                System.out.println(mainPlayer.getName() + " is BANKRUPT!");
            }
        }
    }

    public void inJailAction()
    {
    
        int[] dice = roll();
        boolean isDouble = dice[0] == dice[1];

        if(isDouble)
        {
            playerAtTurn.setInJail(false);
        }
        
    }

    public boolean checkIfWinner()
    {
        for(Player p : players)
        {
            if(p.isInGame() && p.getGPA() > 1000)
            {
                return true;
            }
        }

        return false;
    }

    public void nextTurn()
    {
        int temp = (turn + 1) % numUsers;

        while(true)
        {
            if(players[temp].isInGame())
            {
                turn = (turn + 1) % numUsers;
                playerAtTurn = players[turn];
                break;
            }
            else
            {
                temp = (temp + 1) % numUsers;
            }
        }
    }
    
    public Player[] getPlayers()
    {
        return players;
    }
}
