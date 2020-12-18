public class Cruncher
{
    public Cruncher (Intcode computer, String[] instructions, boolean debug)
    {
        _theComputer = computer;
        _originalInstructions = instructions;
        _debug = debug;
    }

    public String[] crunch (int target)
    {

        for (int i = 0; i < 100; i++)
        {
            String[] instructions = new String[_originalInstructions.length];

            System.arraycopy(_originalInstructions, 0, instructions, 0, _originalInstructions.length);

            for (int j = 0; j < 100; j++)
            {
                String val1 = Integer.toString(i);
                String val2 = Integer.toString(j);

                instructions[1] = val1;
                instructions[2] = val2;

                String result = _theComputer.parseAndExecute(instructions);

                if (Integer.parseInt(result) == target)
                {
                    String[] results = new String[2];

                    results[0] = val1;
                    results[1] = val2;

                    return results;
                }
            }
        }

        return null;
    }

    private Intcode _theComputer = null;
    String[] _originalInstructions = null;
    boolean _debug = false;
}