import java.util.*;

public class Verifier
{
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        /*
         * 1-3 a: abcde
         * 1-3 b: cdefg
         * 2-9 c: ccccccccc
         */

        PasswordData[] data = new PasswordData[3];

        data[0] = new PasswordData(new PasswordPolicy(1, 3, 'a'), "abcde");
        data[1] = new PasswordData(new PasswordPolicy(1, 3, 'b'), "cdefg");
        data[2] = new PasswordData(new PasswordPolicy(2, 9, 'c'), "ccccccccc");

        if (data[0].valid() && !data[1].valid() && data[2].valid())
            return true;

        return false;
    }

    private boolean _debug;
}