public class Util
{
    public static long lcm (long x, long y, long z)
    {
        return lcm(lcm(x, y), z);
    }

    public static long lcm (long i, long y)
    {
        int n;
        int x;
        long s = 1;
        long t = 1;

        for (n = 1;; n++)
        {
            s = i * n;

            for (x = 1; t < s; x++) 
            {
                t = y * x;
            }

            if (s == t)
                break;
        }

        return s;
    }
}