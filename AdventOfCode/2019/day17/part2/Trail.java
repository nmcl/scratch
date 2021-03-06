import java.util.*;

public class Trail extends Map
{
    public Trail (Map toCopy)
    {
        super();

        _originalImage = toCopy._originalImage;
        _debug = toCopy._debug;

        super.createMapFromImage(_originalImage.scannedLines());
    }

    public boolean visited (Coordinate coord)
    {
        if (!valid(coord))
            return false;

        Cell temp = new Cell(coord);
        int index = super._theMap.indexOf(temp);

        temp = super._theMap.get(index);

        switch (temp.getContents())
        {
            case CellId.SCAFFOLDING:
            case CellId.OPEN_SPACE:
                return false;
            default:
                return true;               
        }
    }

    public boolean path (Coordinate coord)
    {
        if (!valid(coord))
            return false;

        Cell temp = new Cell(coord);
        int index = super._theMap.indexOf(temp);

        temp = super._theMap.get(index);

        switch (temp.getContents())
        {
            case CellId.MOVE_LEFT:
            case CellId.MOVE_RIGHT:
                return true;
            default:
                return false;               
        }
    }

    public void changeElement (Coordinate coord, String type)
    {
        Cell temp = new Cell(coord);
        int index = _theMap.indexOf(temp);

        temp = _theMap.get(index);

        temp.setContents(type);
    }
}