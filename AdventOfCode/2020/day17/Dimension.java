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
        _iterations = iterations;
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

    public int cycle ()
    {
        for (int i = 0; i < _iterations; i++)
        {
            HashSet<Cube> nextWorld = new HashSet<Cube>();
            HashSet<Cube> checked = new HashSet<Cube>();

            for (Cube theCube : _theWorld)
            {
                neighbours(nextWorld, checked, theCube, true);
            }

            System.out.println("nextWorld size "+nextWorld.size());
            System.out.println("Checked "+checked.size());
            
            _theWorld = nextWorld;
        }

        return _theWorld.size();
    }

    private void neighbours (HashSet<Cube> nextWorld, HashSet<Cube> checked, Cube theCube, boolean active)
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
                        ThreeDPoint coord = theCube.position();
                        Cube tempCube = new Cube(new ThreeDPoint(coord.getX() + x, coord.getY() + y, coord.getZ() + z));
                
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
                    ThreeDPoint p = new ThreeDPoint(i, _height, 0);

                    if (CubeId.ACTIVE == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Active cell at: "+p);

                        _theWorld.add(new Cube(p, true));
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

    private HashSet<Cube> _theWorld;
    private int _width;
    private int _height;
    private int _iterations;
    private boolean _debug;
}