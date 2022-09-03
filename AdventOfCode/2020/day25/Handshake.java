public class Handshake
{
    public static final long MAGIC_NUMBER = 20201227L;
    public static final long SUBJECT_NUMBER = 7L;
    public static final long START_NUMBER = 1L;

    public Handshake (boolean debug)
    {
        _debug = debug;
    }
    
    public long encryptionKey (long cardPublicKey, long doorPublicKey)
    {
        long count = loopSize(doorPublicKey);
        long curr = START_NUMBER;

        for (int i = 0; i < count; i++)
        {
            curr *= cardPublicKey;
            curr %= MAGIC_NUMBER;
        }

        return curr;
    }

    private long loopSize (long publicKey)
    {
        long curr = START_NUMBER;
        long iter = 0;

        while (value != publicKey)
        {
            curr *= SUBJECT_NUMBER;
            curr %= MAGIC_NUMBER;

            iter++;
        }

        return iter;
    }

    private boolean _debug;
}