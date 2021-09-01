public class Address
{
    // return the next unique address

    public static synchronized final int getNextAddress ()
    {
        _address++;

        return _address;
    }

    public static int _address = -1;  // start at 0

    private Address ()
    {
    }
}