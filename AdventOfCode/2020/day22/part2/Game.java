public class Game
{
    // hard coded that number of players is 2

    public Game (boolean debug)
    {
        _rounds = new Round[2];

        _rounds[0] = new Round(1, debug);
        _rounds[1] = new Round(2, debug);
        
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

                decks[0].add(playerOneCard);
                decks[0].add(playerTwoCard);
            }
            else
            {
                if (_debug)
                    System.out.println("Player 2 wins the round!");

                decks[1].add(playerTwoCard);
                decks[1].add(playerOneCard);
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

    private int playRound (int round)
    {
        return -1;
    }

    private Round[] _rounds;
    private boolean _debug;
}