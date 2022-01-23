import java.io.*;
import java.util.*;

public class Dimension
{
    public static final int TOTAL_NEIGHBOURS = 27;

    public Dimension (String dataFile, int iterations, boolean debug)
    {
        _theWorld = new Vector<Cube>();
        _width = 0;
        _height = 0;
        _iterations = iterations;
        _debug = debug;

        loadLayer(dataFile);  // load the world

        if (_debug)
            printWorld();
    }

    /**
     * During a cycle, all cubes simultaneously change their state according to the following rules:
     * 
     * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
     * Otherwise, the cube becomes inactive.
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
     * Otherwise, the cube remains inactive.
     */

    public int cycle ()
    {
        for (int i = 0; i < _iterations; i++)
        {
            Vector<Cube> nextWorld = new Vector<Cube>();
            Vector<Cube> checked = new Vector<Cube>();

            if (_debug)
                System.out.println("\nIteration "+i);

            for (Cube theCube : _theWorld)
            {
                neighbours(nextWorld, checked, theCube, true);
            }

            if (_debug)
            {
                System.out.println("nextWorld size "+nextWorld.size());
                System.out.println("Checked "+checked.size());
            }

            _theWorld = nextWorld;

            if (_debug)
                printWorld();
        }

        return _theWorld.size();
    }

    private void printWorld ()
    {
        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;
        int minZ = 0, maxZ = 0;

        for (int i = 0; i < _theWorld.size(); i++)
        {
            FourDPoint coord = _theWorld.elementAt(i).position();

            if (coord.getX() < minX)
                minX = coord.getX();

            if (coord.getX() > maxX)
                maxX = coord.getX();

            if (coord.getY() < minY)
                minY = coord.getY();

            if (coord.getY() > maxY)
                maxY = coord.getY();

            if (coord.getZ() < minZ)
                minZ = coord.getZ();

            if (coord.getZ() > maxZ)
                maxZ = coord.getZ();
        }

        for (int z = minZ; z <= maxZ; z++)
        {
            System.out.println("z="+z);

            for (int x = minX; x <= maxX; x++)
            {
                for (int y = minY; y <= maxY; y++)
                {
                    if (_theWorld.contains(new Cube(new FourDPoint(y, x, z, 0))))
                        System.out.print(CubeId.ACTIVE);
                    else
                        System.out.print(CubeId.INACTIVE);
                }

                System.out.println();
            }
        }
    }

    private void neighbours (Vector<Cube> nextWorld, Vector<Cube> checked, Cube theCube, boolean active)
    {
        if (!checked.contains(theCube))
        {
            long numberActive = active ? -1 : 0;
            
            checked.add(theCube);
            
            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {
                    for (int z = -1; z <= 1; z++) 
                    {
                        for (int w = -1; w <= 1; w++)
                        {
                            FourDPoint coord = theCube.position();
                            Cube tempCube = new Cube(new FourDPoint(coord.getX() + x, coord.getY() + y, coord.getZ() + z, coord.getW() + w));

                            if (_theWorld.contains(tempCube))
                            {
                                numberActive++;
                            }
                            else
                            {
                                if (active)
                                {
                                    neighbours(nextWorld, checked, tempCube, false);
                                }
                            }
                        }
                    }
                }
            }
            
            if ((active && (numberActive == 2 || numberActive == 3)) ||
              (!active && numberActive == 3)) 
            {
                nextWorld.add(theCube);
            }
        }
    }

    private void loadLayer (String inputFile)
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded line: "+line);

                _width = line.length();

                for (int i = 0; i < line.length(); i++)
                {
                    FourDPoint p = new FourDPoint(i, _height, 0, 0);

                    if (CubeId.ACTIVE == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Active cell at: "+p);

                        _theWorld.add(new Cube(p));
                    }
                    else
                    {
                        if (_debug)
                            System.out.println("Inactive cell at: "+p);
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

    private Vector<Cube> _theWorld;
    private int _width;
    private int _height;
    private int _iterations;
    private boolean _debug;
}