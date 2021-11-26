import java.util.*;
import java.io.*;

public class Map
{
    public Map (String fileToLoad, boolean debug)
    {
        _theMap = null;
        _debug = debug;

        if (!loadData(fileToLoad))
            System.out.println("Error in loading data file: "+fileToLoad);
    }

    public Map (Map theCopy)
    {
        _theMap = new Vector<MapElement>();
        _debug = theCopy._debug;

        Enumeration<MapElement> iter = theCopy._theMap.elements();

        while (iter.hasMoreElements())
        {
            MapElement element = iter.nextElement();
            
            _theMap.add(new MapElement(element));
        }
    }

    @Override
    public String toString ()
    {
        if (_debug)
            System.out.println("Dimensions <"+_theMap[0].length+", "+_theMap.length+">");

        String str = "";

        for (int x = 0; x < _theMap[0].length; x++)
        {
            for (int y = 0; y < _theMap.length; y++)
            {
                str += _theMap[x][y];
            }

            str += "\n";
        }

        return str;
    }

    private final void loadData (String file)
    {
        BufferedReader reader = null;
        int width = 0;
        int height = 0;
        Vector<MapElement> map = new Vector<MapElement>();

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                char[] asChar = line.toCharArray();
                
                if (width == 0)
                    width = asChar.length;

                for (int i = 0; i < width; i++)
                {
                    // remember x,y coords are reversed for arrays
                    
                    if (asChar[i] == MapElement.TREE)
                    {
                        map.add(new MapElement(new Coordinate(i, height), MapElement.TREE));
                    }
                    else
                    {
                        if (asChar[i] == MapElement.OPEN)
                        {
                            map.add(new MapElement(new Coordinate(i, height), MapElement.OPEN));
                        }
                        else
                        {
                            System.out.println("Unknown character in data file: "+asChar[i]);

                            valid = false;
                        }
                    }
                }

                height++;
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

        _theMap = toCharArray(map, width, height);
    }

    private final char[][] toCharArray (Vector<MapElement> map, int width, int height)
    {
        char[][] toReturn = new char[width][height];
        Enumeration<MapElement> iter = map.elements();

        while (iter.hasMoreElements())
        {
            MapElement element = iter.nextElement();

            toReturn[element.position().getX()][element.position().getY()] = element.type();
        }

        return toReturn;
    }
    
    private char[][] _theMap;
    private boolean _debug;
}