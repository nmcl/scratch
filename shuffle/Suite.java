/**
 * The base interface for all suites of cards.
 */

public interface Suite
{
    public enum Card { ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king };

    public Card getCard () throws NoSuchElementException;
}