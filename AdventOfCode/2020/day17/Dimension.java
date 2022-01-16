import java.io.*;
import java.util.*;

public class Dimension
{
    public Dimension (String dataFile, boolean debug)
    {
        _theWorld = new Vector<ThreeDPoint>();
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
                    ThreeDPoint point = new ThreeDPoint(x, y, z);
                    
                    if (_theWorld.contains(point))
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

        for (int x = coord.getX() -1; x < coord.getX() +2; x++)
        {
            for (int y = coord.getY() -1; y < coord.getZ() +2; y++)
            {
                for (int z = coord.getZ() -1; z < coord.getZ() +2; z++)
                {
                    ThreeDPoint v = new ThreeDPoint(x, y, z);

                    System.out.println(v);

                    //n[index] = new ThreeDPoint(x, y, z);
                    index++;
                }
            }
        }

        System.out.println("index: "+index);

        return null;
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

                        _theWorld.add(p);
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

    private Vector<ThreeDPoint> _theWorld;
    private int _width;
    private int _height;
    private int _minZ;
    private int _maxZ;
    private boolean _debug;
}