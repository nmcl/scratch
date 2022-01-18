import java.io.*;
import java.util.*;

public class Dimension
{
    public static final int TOTAL_NEIGHBOURS = 27;

    public Dimension (String dataFile, boolean debug)
    {
        _theWorld = new Vector<Cube>();
        _width = 0;
        _height = 0;
        _minZ = 0;
        _maxZ = 0;
        _debug = debug;

        loadLayer(dataFile);
    }

    /**
     * During a cycle, all cubes simultaneously change their state according to the following rules:
     * 
     * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
     * Otherwise, the cube becomes inactive.
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
     * Otherwise, the cube remains inactive.
     */

    public void cycle ()
    {
        Vector<Cube> nextWorld = new Vector<Cube>();

        for (int i = 0; i < _theWorld.size(); i++)
        {
            Cube theCube = _theWorld.elementAt(i);
            ThreeDPoint[] cubes = neighbours(theCube);
            int activeCount = -1;  // account for the fact "we" are active

            for (int j = 0; j < cubes.length; j++)
            {
                if (_theWorld.contains(cubes[i]))
                    activeCount++;
            }
        }
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int z = _minZ; z <= _maxZ; z++)
        {
            str += "z="+z+"\n";

            for (int y = 0; y < _height; y++)
            {
                for (int x = 0; x < _width; x++)
                {
                    Cube point = new Cube(new ThreeDPoint(x, y, z));
                    int index = _theWorld.indexOf(point);

                    point = _theWorld.elementAt(index);

                    if (point.isActive())
                        str += CubeId.ACTIVE;
                    else
                        str += CubeId.INACTIVE;
                }

                str += "\n";
            }
        }

        return str;
    }

    public ThreeDPoint[] neighbours (ThreeDPoint coord)
    {
        int index = 0;
        ThreeDPoint[] n = new ThreeDPoint[TOTAL_NEIGHBOURS];

        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    ThreeDPoint v = new ThreeDPoint(coord.getX() + x, coord.getY() + y, coord.getZ() +z);

                    System.out.println(v);

                    n[index] = v;
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

                        _theWorld.add(new Cube(p, false));
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
    private int _minZ;
    private int _maxZ;
    private boolean _debug;
}