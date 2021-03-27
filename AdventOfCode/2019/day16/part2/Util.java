public class Util
{
    public static final int OFFSET = 7;
    public static final int REPEAT_SIZE = 10000;
    
    public static int[] convert (String input)
    {
        int[] result = new int[input.length()];
        char[] sequence = input.toCharArray();

        for (int i = 0; i < result.length; i++)
        {
            result[i] = Character.getNumericValue(sequence[i]);
        }

        return result;
    }

    public static int[] replicate (String input, int times)
    {
        int[] data = convert(input);
        int[] toReturn = new int[data.length * times];
        int index = 0;

        for (int i = 0; i < times; i++)
        {
            System.arraycopy(data, 0, toReturn, index, data.length);

            index += data.length;
        }

        return toReturn;
    }

    public static int[] offset (String input)
    {
        int[] data = convert(input);
        int[] offset = new int[OFFSET];

        for (int i = 0; i < OFFSET; i++)
        {
            offset[i] = data[i];
        }

        return offset;
    }

    private Util ()
    {
    }
    
}