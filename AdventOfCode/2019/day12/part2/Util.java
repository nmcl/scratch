public class Util
{
    public static long lcm (long x, long y, long z)
    {
        return lcm(lcm(x, y), z);
    }

    // https://www.baeldung.com/java-least-common-multiple
    
    public static long lcm (long i, long j)
    {
        if (i == 0 || j == 0)
            return 0;

        long absI = Math.abs(i);
        long absJ = Math.abs(j);
        long absHigherNumber = Math.max(absI, absJ);
        long absLowerNumber = Math.min(absI, absJ);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0)
        {
            lcm += absHigherNumber;
        }

        return lcm;
    }
}