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

    public void changeElement (Coordinate coord, String type)
    {
        Cell temp = new Cell(coord);
        int index = _theMap.indexOf(temp);

        temp = _theMap.get(index);

        temp.setContents(type);
    }
}