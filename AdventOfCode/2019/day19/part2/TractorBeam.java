import java.util.*;

public class TractorBeam
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static final int SPACE_SIZE = 100;

    public static final void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Vector<String> values = IntcodeUtil.readValues(INSTRUCTIONS);
        Grid theGrid = new Grid(values, debug);
	    int sizeOfGrid = theGrid.getBeamGrid().size();

	    for (int i = 0; i < sizeOfGrid; i++)
        {
	       CoordinatePair row = theGrid.getBeamGrid().get(i);
           Coordinate leftCoord = row.getLeft();
           Coordinate rightCoord = row.getRight();

	        if (i < sizeOfGrid - (SPACE_SIZE - 1))
            {
                for (int j = leftCoord.getX(); j <= rightCoord.getX() - (SPACE_SIZE - 1); j++)
                {
                    CoordinatePair nextRow = theGrid.getBeamGrid().get(i + (SPACE_SIZE - 1));

                    if (j >= nextRow.getLeft().getX() && j <= nextRow.getRight().getX())
                    {
                        int xMult = j * 10000;

                        System.out.println("Value obtained: "+(xMult + leftCoord.getY()));
                        System.exit(0);
		            }
		        }
	        }
	    }
    }

    private TractorBeam ()
    {
    }
}