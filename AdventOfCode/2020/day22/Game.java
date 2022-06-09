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
            System.out.println("-- Round "+round+" --");
            System.out.println(decks[0]);
            System.out.println(decks[1]);

            int playerOneCard = decks[0].draw();
            int playerTwoCard = decks[1].draw();

            if (playerOneCard > playerTwoCard)
            {
                decks[0].addToBottom(playerOneCard);
                decks[0].addToBottom(playerTwoCard);
            }
            else
            {
                decks[0].addToBottom(playerTwoCard);
                decks[0].addToBottom(playerOneCard);
            }
        }
    }

    private boolean _debug;
}