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

    public boolean trailTrodden (Coordinate coord)
    {
        Cell temp = new Cell(coord);
        int index = super._theMap.indexOf(temp);

        temp = super._theMap.get(index);

        switch (temp.getContents())
        {
            case CellId.ROBOT_FACING_UP:
            case CellId.ROBOT_FACING_DOWN:
            case CellId.ROBOT_FACING_LEFT:
            case CellId.ROBOT_FACING_RIGHT:
                return true;
            case CellId.SCAFFOLDING:
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