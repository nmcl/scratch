public class Instructions
{
     /**
      * The Opcodes for the computer.
      */

    public static final int ADD = 1;
    public static final int MULTIPLY = 2;
    public static final int INPUT_AND_STORE = 3;
    public static final int OUTPUT = 4;
    public static final int JUMP_IF_TRUE = 5;
    public static final int JUMP_IF_FALSE = 6;
    public static final int LESS_THAN = 7;
    public static final int EQUALS = 8;
    public static final int RELATIVE_BASE = 9;
    public static final int HALT = 99;

    public static final String commandToString (int command)
    {
        switch (command)
        {
            case ADD:
                return "ADD";
            case MULTIPLY:
                return "MULTIPLY";
            case INPUT_AND_STORE:
                return "INPUT_AND_STORE";
            case OUTPUT:
                return "OUTPUT";
            case JUMP_IF_TRUE:
                return "JUMP_IF_TRUE";
            case JUMP_IF_FALSE:
                return "JUMP_IF_FALSE";
            case LESS_THAN:
                return "LESS_THAN";
            case EQUALS:
                return "EQUALS";
            case RELATIVE_BASE:
                return "RELATIVE_BASE";
            case HALT:
                return "HALT";
            default:
                return "UNKNOWN";
        }
    }

    private Instructions ()
    {
        // stop instantiation.
    }
}