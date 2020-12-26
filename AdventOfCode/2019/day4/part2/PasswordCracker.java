public class PasswordCracker
{
	public static final String VALID1 = "112233";
	public static final String VALID2 = "111122";
	public static final String FAILS = "123444";
	public static void main (String[] args)
	{
		char[] digits = new char[6];
		int matchingCriteria = 0;
		boolean debug = false;

		for (int i = 0; i < args.length; i++)
		{
			if ("-help".equals(args[i]))
			{
				System.out.println("Usage: [-help] [-debug] [-verify]");
				System.exit(0);
			}

			if ("-verify".equals(args[i]))
			{
				verify(debug);
				System.exit(0);
			}

			if ("-debug".equals(args[i]))
				debug = true;
		}

		for (int i = _minRange; i <= _maxRange; i++)
		{
			String str = new String(""+i);

			if (adjacentDigits(str, debug) && monotonicallyIncreasing(str, debug))
				matchingCriteria++;
		}

		System.out.println("Matching criteria: "+matchingCriteria);
	}

	private static final void verify (boolean debug)
	{
		if (adjacentDigits(VALID1, debug) && monotonicallyIncreasing(VALID1, debug))
		{
			if (!monotonicallyIncreasing(VALID2, debug) && adjacentDigits(VALID2, debug))
			{
				if (adjacentDigits(FAILS, debug) && monotonicallyIncreasing(FAILS, debug))
					System.out.println("Verified ok!");
				else
					System.out.println("Failed to verify: "+FAILS);
			}
			else
				System.out.println("Failed to verify: "+VALID2);
		}
		else
			System.out.println("Failed to verify: "+VALID1);
	}

	private static final boolean adjacentDigits (String str, boolean debug)
	{
		char[] digits = str.toCharArray();
		int adjacent = 0;

		for (int i = 0; i < digits.length -1; i++)
		{
			if (digits[i] == digits[i+1])
			{
				adjacent++;

				if (i < digits.length -2)
				{
					if (digits[i+1]  == digits[i+2])
						adjacent--;
				}
			}
		}

		if (debug)
			System.out.println("There are "+adjacent+" adjacents for "+str);

		return (adjacent > 0);
	}

	/*
	 * ASCII codes increase so don't need to convert char to int.
	 */

	private static final boolean monotonicallyIncreasing (String str, boolean debug)
	{
		char[] digits = str.toCharArray();

		if (debug)
			System.out.println("Comparing "+digits[0]+" and "+digits[1]);

		if (digits[0] <= digits[1])
		{
			if (debug)
				System.out.println("Comparing "+digits[1]+" and "+digits[2]);

			if (digits[1] <= digits[2])
			{
				if (debug)
					System.out.println("Comparing "+digits[2]+" and "+digits[3]);

				if (digits[2] <= digits[3])
				{
					if (debug)
						System.out.println("Comparing "+digits[3]+" and "+digits[4]);

					if (digits[3] <= digits[4])
					{
						if (debug)
							System.out.println("Comparing "+digits[4]+" and "+digits[5]);

						if (digits[4] <= digits[5])
							return true;
					}
				}
			}
		}

		return false;
	}

	private static final int _minRange = 130254;
	private static final int _maxRange = 678275;
}
