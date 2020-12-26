public class PasswordCracker
{
	public static final String VALID = "111111";
	public static final String FAILS_INCREASE = "223450";
	public static final String FAILS_DOUBLES = "123789";
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

			if ((adjacentDigits(str, debug) > 0) && monotonicallyIncreasing(str, debug))
				matchingCriteria++;
		}

		System.out.println("Matching criteria: "+matchingCriteria);
	}

	private static final void verify (boolean debug)
	{
		if ((adjacentDigits(VALID, debug) > 0) && monotonicallyIncreasing(VALID, debug))
		{
			if (!monotonicallyIncreasing(FAILS_INCREASE, debug) && (adjacentDigits(FAILS_INCREASE, debug) > 0))
			{
				if ((adjacentDigits(FAILS_DOUBLES, debug) == 0) && monotonicallyIncreasing(FAILS_DOUBLES, debug))
					System.out.println("Verified ok!");
				else
					System.out.println("Failed to verify: "+FAILS_DOUBLES);
			}
			else
				System.out.println("Failed to verify: "+FAILS_INCREASE);
		}
		else
			System.out.println("Failed to verify: "+VALID);
	}

	private static final int adjacentDigits (String str, boolean debug)
	{
		char[] digits = str.toCharArray();
		int adjacent = 0;

		for (int i = 0; i < digits.length -1; i++)
		{
			if (digits[i] == digits[i+1])
				adjacent++;
		}

		if (debug)
			System.out.println("There are "+adjacent+" adjacents for "+str);

		return adjacent;
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
