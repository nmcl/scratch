import java.util.*;

public class Game
{
    // hard coded that number of players is 2

    public Game (boolean debug)
    {
        _winningDeck = null;
        _debug = debug;
    }

    public final int play (Deck[] decks)
    {
        int round = 1;
        int game = 1;

        return _winningDeck.score();
    }

    private Deck recursiveCombat (int round, int game, Deck[] decks, int[] offsets)
    {
        Deck[] theDecks = new Deck[2];

        theDecks[0] = new Deck(decks[0], offsets[0]);
        theDecks[1] = new Deck(decks[1], offsets[1]);

        while (!decks[0].isEmpty() && !decks[1].isEmpty())
        {
            if (_debug)
            {
                System.out.println("\n-- Round "+round+" (Game "+game+") --");
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

        return null;
    }

    private Deck _winningDeck;
    private boolean _debug;
}