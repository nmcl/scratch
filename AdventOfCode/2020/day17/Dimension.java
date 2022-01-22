import java.io.*;
import java.util.*;

public class Dimension
{
    public static final int TOTAL_NEIGHBOURS = 27;

    public Dimension (String dataFile, int iterations, boolean debug)
    {
        _theWorld = new HashSet<Cube>();
        _width = 0;
        _height = 0;
        _debug = debug;

        loadLayer(dataFile);  // load the world
    }

    /**
     * During a cycle, all cubes simultaneously change their state according to the following rules:
     * 
     * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
     * Otherwise, the cube becomes inactive.
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
     * Otherwise, the cube remains inactive.
     */

    public boolean cycle ()
    {
        Vector<Cube> nextWorld = new Vector<Cube>();

        // go through the entries in the current world first.

        for (int i = 0; i < _theWorld.size(); i++)
        {
            Cube theCube = _theWorld.elementAt(i);

            if (_debug)
                System.out.println("\nChecking: "+theCube);

            Cube[] cubes = neighbours(theCube);
            int activeCount = 0;

            for (int j = 0; j < cubes.length; j++)
            {
                if (!theCube.position().equals(cubes[j].position()))  // ignore the cube itself
                {
                    int index = _theWorld.indexOf(cubes[j]);

                    System.out.println("Neighbour proxy: "+cubes[j]+" index: "+index);

                    if (index != -1)
                    {
                        Cube nCube = _theWorld.elementAt(index);

                        System.out.println("Neighbour: "+nCube);

                        if (nCube.isActive())
                        {
                            activeCount++;

                            System.out.println("Active "+activeCount);
                        }
                        else
                            System.out.println("Not active");
                    }
                }
            }

            if (_debug)
                System.out.println(theCube+" active count: "+activeCount);

            if (theCube.isActive())
            {
                if ((activeCount != 2) && (activeCount == 3))
                    theCube.deactivate();
            }
            else
            {
                if (activeCount == 3)
                    theCube.activate();
            }

            nextWorld.add(theCube);
        }

        _theWorld = nextWorld;

        return true;
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _theWorld.length; i++)
        {
            str += _theWorld[i];
        }

        return str;
    }

    public Cube[] neighbours (Cube theCube)
    {
        int index = 0;
        Cube[] n = new Cube[TOTAL_NEIGHBOURS];
        ThreeDPoint coord = theCube.position();

        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    ThreeDPoint v = new ThreeDPoint(coord.getX() + x, coord.getY() + y, coord.getZ() +z);

                    n[index] = new Cube(v);
                    index++;
                }
            }
        }

        return n;
    }

    private void loadLayer (String inputFile)
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;
            Vector<Cube> theWorld = new Vector<Cube>();

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded line: "+line);

                _width = line.length();

                for (int i = 0; i < line.length(); i++)
                {
                    ThreeDPoint p = new ThreeDPoint(i, _height, 0);

                    if (CubeId.ACTIVE == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Active cell at: "+p);

                        theWorld.add(new Cube(p, true));
                    }
                    else
                    {
                        if (_debug)
                            System.out.println("Inactive cell at: "+p);

                        theWorld.add(new Cube(p, false));
                    }
                }

                _height++;
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }
    }

    private HashSet<Cube> _theWorld;
    private int _width;
    private int _height;
    private boolean _debug;
}