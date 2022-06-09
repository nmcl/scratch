public class Game
{
    public Game (boolean debug)
    {
        _debug = debug;
    }

    public final void play (Deck[] decks)
    {
        int round = 1;

        while (!decks[0].empty() && !decks[1].empty())
        {
            System.out.println("\n-- Round "+round+" --");
            System.out.println(decks[0]);
            System.out.println(decks[1]);

            int playerOneCard = decks[0].draw();
            int playerTwoCard = decks[1].draw();

            System.out.println("Player 1 plays: "+playerOneCard);
            System.out.println("Player 2 plays: "+playerTwoCard);

            if (playerOneCard > playerTwoCard)
            {
                System.out.println("Player 1 wins the round!");

                decks[0].addToBottom(playerOneCard);
                decks[0].addToBottom(playerTwoCard);
            }
            else
            {
                System.out.println("Player 2 wins the round!");

                decks[1].addToBottom(playerTwoCard);
                decks[1].addToBottom(playerOneCard);
            }

            round++;
        }

        System.out.println("\n== Post-game results ==");
        System.out.println(decks[0]);
        System.out.println(decks[1]);
    }

    private boolean _debug;
}