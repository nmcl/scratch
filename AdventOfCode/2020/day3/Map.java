import java.util.*;
import java.io.*;

public class Map
{
    public Map (String fileToLoad, boolean debug)
    {
        _theMap = new Vector<MapElement>();
        _debug = debug;

        if (!loadData(fileToLoad))
            System.out.println("Error in loading data file: "+fileToLoad);
    }

    public Map (Map theCopy)
    {
        _theMap = new Vector<MapElement>();
        _height = theCopy._height;
        _width = theCopy._width;
        _debug = theCopy._debug;

        Enumeration<MapElement> iter = theCopy._theMap.elements();

        while (iter.hasMoreElements())
        {
            MapElement element = iter.nextElement();
            
            _theMap.add(new MapElement(element));
        }
    }

    public char[][] toCharArray ()
    {
        char[][] toReturn = new char[_width][_height];
        Enumeration<MapElement> iter = _theMap.elements();

        while (iter.hasMoreElements())
        {
            MapElement element = iter.nextElement();

            toReturn[element.position().getX()][element.position().getY()] = element.type();
        }

        return toReturn;
    }

    @Override
    public String toString ()
    {
        if (_debug)
            System.out.println("Dimensions <"+_width+", "+_height+">");

        Enumeration<MapElement> iter = _theMap.elements();
        String str = "";

        while (iter.hasMoreElements())
        {
            MapElement theEntry = iter.nextElement();

            str += theEntry.type();

            System.out.println(theEntry.position());

            if (theEntry.position().getX() == _width -1)
                str += "\n";
        }

        return str;
    }

    private final boolean loadData (String file)
    {
        BufferedReader reader = null;
        boolean valid = true;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                char[] asChar = line.toCharArray();
                
                if (_width == 0)
                    _width = asChar.length;

                for (int i = 0; i < _width; i++)
                {
                    // remember x,y coords are reversed for arrays
                    
                    if (asChar[i] == MapElement.TREE)
                    {
                        _theMap.add(new MapElement(new Coordinate(i, _height), MapElement.TREE));
                    }
                    else
                    {
                        if (asChar[i] == MapElement.OPEN)
                        {
                            _theMap.add(new MapElement(new Coordinate(i, _height), MapElement.OPEN));
                        }
                        else
                        {
                            System.out.println("Unknown character in data file: "+asChar[i]);

                            valid = false;
                        }
                    }
                }

                _height++;
            }
        }
        catch (Throwable ex)
        {
            valid = false;

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

        return valid;
    }
    
    private Vector<MapElement> _theMap;
    private int _height = 0;
    private int _width = 0;
    private boolean _debug;
}