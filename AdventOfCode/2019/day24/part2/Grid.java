import java.util.*;
import java.io.*;

/*
 * Infinite plane.
 */

public class Grid
{
    public Grid (String fileName, boolean debug)
    {
        this(GridData.DEFAULT_HEIGHT, GridData.DEFAULT_WIDTH, fileName, debug);
    }

    public Grid (int height, int width, String fileName, boolean debug)
    {
        _theWorld = new Level[GridData.DEFAULT_LEVELS*2 +1];
        _height = height;
        _width = width;
        _debug = debug;

        int layer = -GridData.DEFAULT_LEVELS;

        // initialise

        for (int i = 0; i < GridData.DEFAULT_LEVELS*2 +1; i++)
        {
            _theWorld[i] = new Level(layer, _height, _width, _debug);

            layer++;
        }

        loadWorld(fileName);
    }

    public final long totalBugCount ()
    {
        long total = 0;

        for (int i = 0; i < _theWorld.length; i++)
        {
            total += _theWorld[i].bugCount();
        }

        return total;
    }

    /**
     * Each minute, The bugs live and die based on the number of bugs in the four adjacent tiles:
     *
     * - A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
     * - An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
     * 
     * Otherwise, a bug or empty space remains the same. (Tiles on the edges of the grid have fewer than
     * four adjacent tiles; the missing tiles count as empty space.) This process happens in every location
     * simultaneously; that is, within the same minute, the number of adjacent bugs is counted for every tile
     * first, and then the tiles are updated.
     * 
     * For the infinite plane ...
     * 
     * Tile 19 has four adjacent tiles: 14, 18, 20, and 24.
     * Tile G has four adjacent tiles: B, F, H, and L.
     * Tile D has four adjacent tiles: 8, C, E, and I.
     * Tile E has four adjacent tiles: 8, D, 14, and J.
     * Tile 14 has eight adjacent tiles: 9, E, J, O, T, Y, 15, and 19.
     * Tile N has eight adjacent tiles: I, O, S, and five tiles within the sub-grid marked ?.
     */

    public void evolve ()
    {
        HashSet<ThreeDPoint> allBugs = mergeLayers();
        HashSet<ThreeDPoint> evolvedBugs = new HashSet<ThreeDPoint>();

        /*
         * Figure out which levels actually exist at this stage.
         */

        int minZ = minimumLayer(allBugs);
        int maxZ = maximumLayer(allBugs);

        if (_debug)
            System.out.println("Starting bugs: "+allBugs);

        for (int x = 0; x < GridData.DEFAULT_WIDTH; x++)
        {
            for (int y = 0; y < GridData.DEFAULT_HEIGHT; y++)
            {
                if ((x != GridData.CENTRE_X) || (y != GridData.CENTRE_Y))
                {
                    for (int z = minZ; z <= maxZ; z++)  // go through the layers
                    {
                        ThreeDPoint coord = new ThreeDPoint(x, y, z);
                        long totalBugs =  adjacentTileCoordinates(coord).stream()
                                                .filter(p -> allBugs.contains(p))
                                                .count();

                        if (!allBugs.contains(coord))
                        {
                            if ((totalBugs == 1) || (totalBugs == 2))
                                evolvedBugs.add(coord);
                        }
                        else
                        {
                            if (totalBugs == 1)
                                evolvedBugs.add(coord);
                        }
                    }
                }
            }
        }

        if (_debug)
            System.out.println("Evolved bugs: "+evolvedBugs);

        splitLayers(evolvedBugs);  // split to make printing easier
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _theWorld.length; i++)
        {
            str += "\nDepth "+_theWorld[i].getLevel()+":\n";
            str += _theWorld[i];
        }

        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Grid temp = (Grid) obj;

            if (_theWorld.length == temp._theWorld.length)
            {
                boolean same = true;

                for (int i = 0; (i < _theWorld.length) && same; i++)
                {
                    if (!_theWorld[i].equals(temp._theWorld[i]))
                        same = false;
                }

                return same;
            }
        }

        return false;
    }

    private final int minimumLayer (HashSet<ThreeDPoint> allBugs)
    {
        return allBugs.stream().mapToInt(p -> p.getZ()).min().orElse(0) - 1;
    }

    private final int maximumLayer (HashSet<ThreeDPoint> allBugs)
    {
        return allBugs.stream().mapToInt(p -> p.getZ()).max().orElse(0) + 1;
    }

    private final HashSet<ThreeDPoint> mergeLayers ()
    {
        HashSet<ThreeDPoint> merged = new HashSet<ThreeDPoint>();

        for (int i = 0; i < _theWorld.length; i++)
        {
            merged.addAll(_theWorld[i].getBugs());
        }

        return merged;
    }

    private final void splitLayers (HashSet<ThreeDPoint> merged)
    {
        int totalLayers = Math.abs(minimumLayer(merged)) + maximumLayer(merged) +1;
        int layer = minimumLayer(merged);

        for (int i = 0; i < totalLayers; i++)
        {
            _theWorld[i] = new Level(layer, _height, _width, _debug);

            layer++;
        }

        Iterator<ThreeDPoint> iter = merged.iterator();

        System.out.println("Splitting "+minimumLayer(merged)+" "+maximumLayer(merged));

        while (iter.hasNext())
        {
            ThreeDPoint position = iter.next();
            System.out.println("position "+position);
            int index = position.getZ() + totalLayers;

            _theWorld[index].addBug(position);
        }
    }

    private HashSet<ThreeDPoint> adjacentTileCoordinates (ThreeDPoint position)
    {
        HashSet<ThreeDPoint> tiles = new HashSet<ThreeDPoint>();

        // could generate invalid coordinates but we'll deal with that later ...

        tiles.add(new ThreeDPoint(position.getX() - 1, position.getY(), position.getZ()));
        tiles.add(new ThreeDPoint(position.getX() + 1, position.getY(), position.getZ()));
        tiles.add(new ThreeDPoint(position.getX(), position.getY() - 1, position.getZ()));
        tiles.add(new ThreeDPoint(position.getX(), position.getY() + 1, position.getZ()));

        if (position.getX() == 0)
            tiles.add(GridData.leftOuterEdge(position));

        if (position.getX() == GridData.DEFAULT_HEIGHT -1)
            tiles.add(GridData.rightOuterEdge(position));

        if (position.getY() == 0)
            tiles.add(GridData.topOuterEdge(position));

        if (position.getY() == GridData.DEFAULT_HEIGHT -1)
            tiles.add(GridData.bottomOuterEdge(position));

        if ((position.getX() == GridData.CENTRE_X) && (position.getY() == GridData.TOP_INNER_EDGE_Y))
            GridData.topInnerEdge(position, tiles);

        if ((position.getX() == GridData.CENTRE_X) && (position.getY() == GridData.BOTTOM_INNER_EDGE_Y))
            GridData.bottomInnerEdge(position, tiles);

        if ((position.getX() == GridData.LEFT_INNER_EDGE_X) && (position.getY() == GridData.CENTRE_Y))
            GridData.leftInnerEdge(position, tiles);

        if ((position.getX() == GridData.RIGHT_INNER_EDGE_X) && (position.getY() == GridData.CENTRE_Y))
            GridData.rightInnerEdge(position, tiles);

        // Nove remove any invalid points
  
        pruneInvalid(tiles);

        return tiles;
    }

    private void pruneInvalid (HashSet<ThreeDPoint> tiles)
    {
        // remove the nested grid marker/coordinate

        tiles.removeIf(p -> p.getX() == GridData.CENTRE_X && p.getY() == GridData.CENTRE_Y);

        // now remove anything with invalid coordinates

        tiles.removeIf(p -> p.getX() < 0 || p.getX() > (GridData.DEFAULT_WIDTH -1) || p.getY() < 0 || p.getY() > (GridData.DEFAULT_HEIGHT -1));
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;
        int h = 0;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded line: "+line);
                    
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.BUG == line.charAt(i))
                    {
                        if (_debug)
                            System.out.println("Bug at "+i+" "+h);

                        _theWorld[GridData.DEFAULT_LEVELS].addBug(new ThreeDPoint(i, h, 0));
                    }
                }

                h++;
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

    private Level[] _theWorld;
    private int _height;
    private int _width;
    private boolean _debug;
}