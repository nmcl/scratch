import java.util.*;

public class CO2
{
    public static final String getCO2 (Vector<String> data, int index)
    {
        int ones = 0;
        int zeros = 0;

        for (int i = 0; i < data.size(); i++)
        {
            if (data.elementAt(i).charAt(index) == '1')
                ones++;
            else
                zeros++;
        }

        char search = '0';

        if (ones > zeros)
            search = '1';

        Vector<String> subset = new Vector<String>();

        for (int j = 0; j < data.size(); j++)
        {
            if (data.elementAt(j).charAt(index) == search)
                subset.add(data.elementAt(j));
        }

        if (subset.size() == 1)
            return subset.elementAt(0);
        else
            return getCO2(subset, index++);
    }
}