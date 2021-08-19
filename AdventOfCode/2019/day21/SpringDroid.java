import java.util.*;

public class SpringDroid
{
    public static final int MAX_INSTRUCTIONS = 15;

    /*
     * Jump if there is a hole on A or on B or on C and there is no hole on D.
     *
     * 'OR A J', //  J = A
     * 'AND B J', // J = A AND B
     * 'AND C J', // J = A AND B AND C
     * 'NOT T J', // J = !A OR !B OR !C
     * 'AND D J', // J = (!A OR !B OR !C) AND D
     * 'WALK'
     */

    public SpringDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
    }

    private Intcode _computer;
    private boolean _debug;
}