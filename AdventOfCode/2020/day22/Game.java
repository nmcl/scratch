public class Game
{
    public Game (boolean debug)
    {
        _debug = debug;
    }

    public final int play (Deck[] decks)
    {
        int round = 1;

        while (!decks[0].empty() && !decks[1].empty())
        {
            if (_debug)
            {
                System.out.println("\n-- Round "+round+" --");
                System.out.println(decks[0]);
                System.out.println(decks[1]);
            }

            int playerOneCard = decks[0].draw();
            int playerTwoCard = decks[1].draw();

            if (_debug)
            {
                System.out.println("Player 1 plays: "+playerOneCard);
                System.out.println("Player 2 plays: "+playerTwoCard);
            }

            if (playerOneCard > playerTwoCard)
            {
                if (_debug)
                    System.out.println("Player 1 wins the round!");

                decks[0].addToBottom(playerOneCard);
                decks[0].addToBottom(playerTwoCard);
            }
            else
            {
                if (_debug)
                    System.out.println("Player 2 wins the round!");

                decks[1].addToBottom(playerTwoCard);
                decks[1].addToBottom(playerOneCard);
            }

            round++;
        }

        if (_debug)
        {
            System.out.println("\n== Post-game results ==");
            System.out.println(decks[0]);
            System.out.println(decks[1]);
        }

        if (!decks[0].empty())
            return decks[0].score();
        else
            return decks[1].score();
    }

    private boolean _debug;
}