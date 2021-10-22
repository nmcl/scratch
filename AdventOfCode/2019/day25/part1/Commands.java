public class Commands
{
    public static final String NORTH = "north";
    public static final String SOUTH = "south";
    public static final String EAST = "east";
    public static final String WEST = "west";

    public static final String TAKE = "take";
    public static final String DROP = "drop";
    public static final String INVENTORY = "inv";

    public static final String QUIT = "quit";

    public static final String getCommands ()
    {
        // TODO add numbers as static values too

        return NORTH+"\n"+SOUTH+"\n"+EAST+"\n"+WEST+"\n"+TAKE+"\n"+DROP+"\n"+INVENTORY+"\n"+QUIT+"\n";
    }

    private Commands ()  // prevent instantiation.
    {
    }
}