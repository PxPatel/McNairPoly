A simplified form of Monopoly, relating to McNair HS

McNairPoly
    private Player[] players
    private users
    private Scanner scan
    private playerAtTurn
    private Property[] board


    Constructor
        <> Collect Player names and store in Array
        <> Each index in the array should be a new Player object
        <> Each player will be initalized with a set number of money
        <>Each player will be initalized on the same square
        <> Initializes the board with Property Objects

    <> private helper roll()
        Random 2 dice rolls

    <> move() 
        rolls dice
        get current position of player at turn
        get the new position using modulus
        set the player's new position

    <> isOwned()
        get currPos of the player at turn
        use isOwned getter method on the property at that position
        e

    <> action()
        check if property landed is special or not
        buy()
        payrent()

    <> checkIfWinner()

    <> nextTurn()


Player
    private name
    private pos
    private GPA
    private propertiesOwned
    private isInGame

    <> bankrupt(Player obj)
    <> buy()
    <> payRent()

Space
    Represents each space on the board
    private name
    private location
    private type

Property extends Spaces
    private isSpecial
    private isTax;
    private isDoubleLunch;
    private isDetention;
    private isStudying;
    private cost
    private isOwned
    private owner
    private rent;
    




