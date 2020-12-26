public class PasswordCracker
{
	public static final String VALID = "111111";
	public static final String FAILS_INCREASE = "223450";
	public static final String FAILS_DOUBLES = "123789";
	public static void main (String[] args)
	{
		char[] digits = new char[6];
		int matchingCriteria = 0;
		String currentValue = null;

		for (int i = 0; i < args.length; i++)
		{
			if ("-help".equals(args[i]))
			{
				System.out.println("Usage: [-help] [-verify]");
				System.exit(0);
			}

			if ("-verify".equals(args[i]))
			{
				verify();
				System.exit(0);
			}
		}

		for (int i = _minRange; i <= _maxRange; i++)
		{
			String str = new String(""+i);

			System.out.println("got: "+str);
			System.out.println("Adjacent: "+adjacentDigits(str));
			System.out.println("Increaing: "+monotonicallyIncreasing(str));
		}
	}

	private static final void verify ()
	{
		if ((adjacentDigits(VALID) == 1) && monotonicallyIncreasing(VALID))
		{
			if ((adjacentDigits(FAILS_INCREASE) == 1) && !monotonicallyIncreasing(FAILS_INCREASE))
			{
				if (monotonicallyIncreasing(FAILS_DOUBLES) && (adjacentDigits(FAILS_DOUBLES) == 0))
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

	private static final int adjacentDigits (String str)
	{
		char[] digits = str.toCharArray();
		int adjacent = 0;

		for (int i = 0; i < digits.length -1; i++)
		{
			if (digits[i] == digits[i+1])
				adjacent++;
		}

		return adjacent;
	}

	/*
	 * ASCII codes increase so don't need to convert char to int.
	 */

	private static final boolean monotonicallyIncreasing (String str)
	{
		char[] digits = str.toCharArray();

		if (digits[0] >= digits[1])
		{
			if (digits[1] >= digits[2])
			{
				if (digits[2] >= digits[3])
				{
					if (digits[3] >= digits[4])
					{
						if (digits[4] >= digits[5])
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
