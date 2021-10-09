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
        return "1: "+NORTH+"\n2: "+SOUTH+"\n3: "+EAST+"\n4: "+WEST+"\n5: "+TAKE+"\n6: "+DROP+"\n7: "+INVENTORY+"\n8: "+QUIT;
    }

    private Commands ()  // prevent instantiation.
    {
    }
}