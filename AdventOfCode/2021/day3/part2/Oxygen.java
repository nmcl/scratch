import java.util.*;

public class Oxygen
{
    public static final String getOxygen (Vector<String> data, int index)
    {
        int ones = 0;
        int zeros = 0;

        System.out.println("data size "+data.size());
        System.out.println("index "+index);

        for (int i = 0; i < data.size(); i++)
        {
            System.out.println("Checking "+data.elementAt((i)));

            if (data.elementAt(i).charAt(index) == '1')
                ones++;
            else
                zeros++;
        }

        System.out.println("Number of ones: "+ones);
        System.out.println("Number of zeros: "+zeros);

        char search = '0';

        if (ones < zeros)
            search = '1';

        Vector<String> subset = new Vector<String>();

        for (int j = 0; j < data.size(); j++)
        {
            if (data.elementAt(j).charAt(index) == search)
                subset.add(data.elementAt(j));
        }

        System.out.println("subset: "+subset.size());

        if (subset.size() == 1)
            return subset.elementAt(0);
        else
            return getOxygen(subset, ++index);
    }
}