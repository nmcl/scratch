public class Intcode
{
    public enum OPCODE {
        ADD(1), MULTIPLY(2), HALT(99);

        OPCODE (int val)
        {
            this._val = val;
        }

        public int getVal ()
        {
            return _val;
        }

        private int _val;
    }

    public static final String DELIMITER = ",";
    
    public static void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help]");
                System.exit(0);
            }
        }
    }

    private static final String DATA_FILE = "instructions.txt";
}