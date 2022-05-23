import java.util.Scanner;

public class McNairPoly
{
    private Player[] players;
    private int numUsers;
    private int turn;
    private Player playerAtTurn;

    private Scanner scan = new Scanner(System.in);
    
    private Card[] board;
    private final int BOARD_SIZE = 20; //6 x 6 board

    private final int waitTimer = 750; 

    public McNairPoly()
    {
        turn = 0;

        int num = 100;
        while(num <= 1 || num > 6)
        {
            System.out.print("How many people are playing (> 1 and < 7)? ");
            num = scan.nextInt(); 
        }

        //To consume the empty line and not cause errors regarding the Scanner class
        scan.nextLine();

        players = new Player[num];
        numUsers = num;
         // Asks for player names and assigns them
		for (int i = 0; i < numUsers; i++) 
        {
			System.out.print("What is Player " + (i + 1) + "'s name? ");
			String name = scan.nextLine();
			players[i] = new Player(name);
		}

        //Initalizes the array and creates a Property objects
        initializeBoard();

        playerAtTurn = players[0];
    }

    public void initializeBoard()
    {
        board = new Card[BOARD_SIZE];

        board[0] = new Special("Go", 0, false, false, false, true);
        board[1] = new Property("Gym", 1, 5, 0);
        board[2] = new Property("Mythology", 2, 7, 0);
        board[3] = new Special("Small Tax - Forgot HW", 3, true, false, false, false);
        board[4] = new Property("French 1", 4, 10, 0);
        board[5] = new Special("Big Tax - Dress Coded", 5, true, false, false, false);

        board[6] = new Property("Driver\'s Ed", 6, 13, 0);
        board[7] = new Property("Lit 3", 7, 16, 0);
        board[8] = new Property("Chem Honors", 8, 18, 0);
        board[9] = new Property("AP Environmental Science", 8, 20, 0);

        board[10] = new Special("Detention", 9, false, false, true, false);
        board[11] = new Property("AP Lit", 10, 30, 0);
        board[12] = new Special("Small Tax - Forgot Project", 11, true, false, false, false);
        board[13] = new Property("AP USH", 12, 35, 0);
        board[14] = new Property("AP Calc AB", 13, 37, 0);
        board[15] = new Special("Double Lunch", 14, false, true, false, false);
        
        board[16] = new Special("Small Tax - Late to Class", 16, true, false, false, false);
        board[17] = new Property("AP Physics 1", 17, 56, 0);
        board[18] = new Property("AP Chem", 18, 64, 0);
        board[19] = new Property("AP Calc BC", 19, 70, 0); 
    }

    private void sleep()
    {
        try
        {
            Thread.sleep(waitTimer);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
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
        System.out.print("[Rolling...] ==> ");

        sleep();

        System.out.print(playerAtTurn.getName() + " rolled a [" + a + "] [" + b + "]");

        return total;
    }

    public void move()
    {
        System.out.println("\n[New Turn] " + playerAtTurn.getName() + ", would you like to roll? [ENTER TO CONTINUE]");
        scan.nextLine();

        int[] dice = roll();
        int roll = dice[0] + dice[1];
        System.out.println(" ==> " + roll);

        int currPos = playerAtTurn.getPos();
        int newPos = (currPos + roll) % board.length;
        playerAtTurn.setPos(newPos);

        if(currPos > newPos)
        {
            playerAtTurn.passGo();
        }
        sleep();
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
        Card landed = board[mainPlayer.getPos()];

        sleep(2000);
        if(landed.isSpecial())
        {
       
            if(((Special) landed).isTax())
            {
                mainPlayer.payTax(landed);
            }
            // else if(landed.isDoubleLunch)
            // {
            //     mainPlayer.inDoubleLunch();
            // }
            else if(((Special) landed).isDetention())
            {
                mainPlayer.putInDetention();
                sleep();
                System.out.println("\n[DETENTION] OH NO! You are now in Detention!");
            }
            
        }
        else if(!((Property) landed).getIsOwned())
        {
            while(true)
            {
                System.out.print("\nWould you like to buy {" + landed.getName() + "}? (Y/N) ");
                String choice = scan.nextLine().toUpperCase();
                if(choice.equals("Y") && playerAtTurn.getGPA() >= ((Property) landed).getCost())
                {
                    players[turn].buy(landed);
                    sleep();
                    System.out.println("\n[BOUGHT] " + mainPlayer.getName() + " just bought " + landed.getName() + "!");
                    break;
                }
                else if(choice.equals("Y"))
                {
                    sleep();
                    System.out.println("\nSorry, you don't have enough GPA to buy " + landed.getName());
                    break;
                }
                else if(choice.equals("N"))
                {
                    break;
                }
            }
        }
        else if(((Property) landed).getIsOwned())
        {
            if(players[turn].getGPA() >= ((Property) landed).getRent())
            {
                players[turn].payRent(landed);
                sleep();
                System.out.println("\n[RENT] " + mainPlayer.getName() + " has payed $" + ((Property) landed).getRent() + " to " + ((Property) landed).getOwner().getName());
            }
            else
            {
                players[turn].bankrupt(((Property) landed).getOwner());
                sleep();
                System.out.println(mainPlayer.getName() + " is BANKRUPT!");
            }
        }
    }

    public void inJailAction()
    {
        System.out.println("\n[New Turn] " + playerAtTurn.getName() + ", would you like to roll? [ENTER TO CONTINUE]");
        scan.nextLine();
    
        int[] dice = roll();
        boolean isDouble = dice[0] == dice[1];

        if(isDouble)
        {
            playerAtTurn.setInJail(false);
            playerAtTurn.setDaysInJail(0);
            sleep();
            System.out.println("\n\n[FREEDOM] You rolled a double and are now out of Detention!");
        }
        else if(playerAtTurn.getDaysInJail() == 2)
        {
            playerAtTurn.setInJail(false);
            playerAtTurn.setDaysInJail(0);
            sleep();
            System.out.println("\n\n[FREEDOM] You spent 2 days and are now out of Detention!");
        }
        else
        {
            playerAtTurn.setDaysInJail(playerAtTurn.getDaysInJail() + 1);
            sleep();
        }
        
    }

    public boolean checkIfWinner()
    {
        for(Player p : players)
        {
            if(p.getIsInGame() && p.getGPA() >= 375 )
            {
                return true;
            }
        }

        return false;
    }

    public void nextTurn()
    {
        sleep();
        int temp = (turn + 1) % numUsers;

        while(true)
        {
            if(players[temp].getIsInGame())
            {
                turn = temp;
                playerAtTurn = players[turn];
                break;
            }
            else
            {
                temp = (temp + 1) % numUsers;
            }
        }

        System.out.println("\n------------------------------------------------------");
    }
    
    public Player[] getPlayers()
    {
        return players;
    }

    public void getBoard()
    {
        for(Card i : board)
        {
            System.out.println(i);
        }
    }
}
