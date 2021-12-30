public class Bus
{
    public Bus (int id, int earliestDeparture)
    {
        _id = id;
        _nextDeparture = earliestDeparture - (earliestDeparture % _id);

        if (_nextDeparture < earliestDeparture)
            _nextDeparture += _id;
    }

    public final int nextDeparture ()
    {
        return _nextDeparture;
    }

    public final int getID ()
    {
        return _id;
    }

    @Override
    public String toString ()
    {
        return "Bus "+_id+" departure "+_nextDeparture;
    }

    private int _id;
    private int _nextDeparture;
}