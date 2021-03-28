public class Util
{
    public static final int OFFSET = 7;
    public static final int REPEAT_SIZE = 3;//10000;
    public static final int PHASES = 100;
    public static final int MESSAGE_SIZE = 8;

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

    public static long offset (String input)
    {
        int[] data = convert(input);
        String offsetStr = input.substring(0, Util.OFFSET);

        return Long.parseLong(offsetStr);
    }

    public static int[] message (int[] input, long offset)
    {
        return null;
    }

    public static void printSignal (int[] signal, int split)
    {
        for (int i = 0; i < signal.length; i++)
        {
            if ((split > 0) && ((i > 0) && (i % split == 0)))
                System.out.println();

            System.out.print(signal[i]);
        }

        System.out.println();
    }

    private Util ()
    {
    }
}