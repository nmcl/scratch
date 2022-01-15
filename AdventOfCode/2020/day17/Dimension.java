import java.io.*;
import java.util.*;

public class Dimension
{
    public static final int MAX_LAYERS = 41;  // 20 layers either size of layer 0

    public Dimension (String dataFile, boolean debug)
    {
        _layers = new Layer[MAX_LAYERS];
        _layerZero = (MAX_LAYERS / 2) +1;
        _debug = debug;

        for (int i = 0; i < MAX_LAYERS; i++)
            _layers[i] = null;

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

        for (int i = 0; i < _layers.length; i++)
        {
            if (_layers[i] != null)
            {
                str += _layers[i]+"\n";
            }
        }

        return str;
    }

    private void loadLayer (String inputFile)
    {
        BufferedReader reader = null;
        int h = 0;
        int w = 0;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;
            Vector<Character> world = new Vector<Character>();

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded line: "+line);

                w = line.length();

                for (int i = 0; i < line.length(); i++)
                {
                    if (CubeId.ACTIVE == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Active cell at "+i+" "+h);

                        world.add(CubeId.ACTIVE);
                    }
                    else
                    {
                        if (_debug)
                            System.out.println("Inactive cell at "+i+" "+h);

                        world.add(CubeId.INACTIVE);
                    }
                }

                h++;
            }

            Layer theLayer = new Layer(0, h, w, _debug);
            int index = 0;

            for (int y = 0; y < h; y++)
            {
                for (int x = 0; x < w; x++)
                {
                    if (world.elementAt(index) == CubeId.ACTIVE)
                        theLayer.activate(y, x);

                    index++;
                }
            }

            _layers[_layerZero] = theLayer;
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

    private Layer[] _layers;
    private int _layerZero;
    private boolean _debug;
}