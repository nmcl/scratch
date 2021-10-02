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

        int minZ = allBugs.stream().mapToInt(p -> p.getZ()).min().orElse(0) - 1;
        int maxZ = allBugs.stream().mapToInt(p -> p.getZ()).max().orElse(0) + 1;

        System.out.println("Starting bugs "+allBugs);

        for (int x = 0; x < GridData.DEFAULT_WIDTH; x++)
        {
            for (int y = 0; y < GridData.DEFAULT_HEIGHT; y++)
            {
                if ((x != GridData.CENTRE_X) && (y != GridData.CENTRE_Y))
                {
                    for (int z = minZ; z <= maxZ; z++)  // go through the layers
                    {
                        System.out.println("z is "+z);
                        ThreeDPoint coord = new ThreeDPoint(x, y, z);
                        long totalBugs = numberOfBugs(allBugs, coord);

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

        //System.out.println("ending bugs "+evolvedBugs);

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
        int layer = -GridData.DEFAULT_LEVELS;

        for (int i = 0; i < GridData.DEFAULT_LEVELS*2 +1; i++)
        {
            _theWorld[i] = new Level(layer, _height, _width, _debug);

            layer++;
        }

        Iterator<ThreeDPoint> iter = merged.iterator();

        while (iter.hasNext())
        {
            ThreeDPoint position = iter.next();
            int index = position.getZ() + GridData.DEFAULT_LEVELS;

            _theWorld[index].addBug(position);
        }
    }

    private HashSet<ThreeDPoint> adjacentTileCoordinates (ThreeDPoint position)
    {
        HashSet<ThreeDPoint> tiles = new HashSet<ThreeDPoint>();
  
        System.out.println("Starting coord "+position);

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

        System.out.println("tiles "+tiles);

        return tiles;
    }

    private void pruneInvalid (HashSet<ThreeDPoint> tiles)
    {
        // remove the nested grid marker/coordinate

        tiles.removeIf(p -> p.getX() == GridData.CENTRE_X && p.getY() == GridData.CENTRE_Y);

        // now remove anything with invalid coordinates

        tiles.removeIf(p -> p.getX() < 0 || p.getX() > (GridData.DEFAULT_WIDTH -1) || p.getY() < 0 || p.getY() > (GridData.DEFAULT_HEIGHT -1));
    }

    private long numberOfBugs (HashSet<ThreeDPoint> allBugs, ThreeDPoint position)
    {
        return adjacentTileCoordinates(position).stream()
                    .filter(p -> allBugs.contains(p))
                    .count();
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
                System.out.println("line "+line);
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.BUG == line.charAt(i))
                    {
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