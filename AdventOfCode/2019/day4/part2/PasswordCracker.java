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
			{
				matchingCriteria++;

				if (debug)
					System.out.println("Matched:"+ str);
			}
		}

		System.out.println("Matching criteria: "+matchingCriteria);
	}

	private static final void verify (boolean debug)
	{
		if (adjacentDigits(VALID1, debug) && monotonicallyIncreasing(VALID1, debug))
		{
			if (debug)
				System.out.println("Verified "+VALID1);

			if (adjacentDigits(VALID2, debug) && monotonicallyIncreasing(VALID2, debug))
			{
				if (debug)
					System.out.println("Verified "+VALID2);

				if (!adjacentDigits(FAILS, debug) && monotonicallyIncreasing(FAILS, debug))
				{
					if (debug)
						System.out.println("Verified "+FAILS);

					System.out.println("Verified ok!");
				}
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
		int sequenceStart = -1;

		if (debug)
			System.out.println("\nScanning "+str);

		for (int i = 1; i < digits.length; i++)
		{
			if (digits[i-1] == digits[i])
			{
				int duplicate = 2;
				boolean stop = false;
				int j;

				if (debug)
				{
					System.out.println("Found dupliocate "+digits[i-1]+" at "+i);

					System.out.println("Starting sequence scan from "+(i+1));
				}

				for (j = i+1; (j < digits.length) && !stop; j++)
				{
					/*
					 * How many times does the digit appear in sequence?
					 */

					if (debug)
						System.out.println("Comparing digits "+j+" and "+i);

					if (digits[j] == digits[i])
					{
						duplicate++;

						if (debug)
							System.out.println("Identical.");
					}
					else
					{
						stop = true;
						i = j;

						if (debug)
							System.out.println("Different.");
					}
				}

				/*
				 * If we get here without dropping out of the loop
				 * early then we're at the end of the digit stream so
				 * break out of the outer loop too.
				 */

				if (!stop && (j == digits.length))
					i = digits.length;

				if (duplicate == 2)
					adjacent++;
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
