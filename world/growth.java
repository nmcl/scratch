public class growth
{
    public static void main (String[] args)
    {
	/*
	 * y = a(1+r)^x
	 */

	double y = 0;
	double a = 1; // initial value
	double r = 0.3; // infection rate
	int x = 0;
	int t = 100;
	int p = 0;
	
	for (int i = 0; i < args.length; i++)
        {
	    if ("-help".equals(args[i]))
	    {
		System.out.println("growth [-infection <value>] [-time <value>] [-population <value>] [-help]");
		System.exit(0);
	    }

	    if ("-population".equals(args[i]))
	    {
		Integer pop = Integer.parseInt(args[i+1]);

		p = pop.intValue();
	    }
	    
	    if ("-infection".equals(args[i]))
	    {
		Double inf = Double.parseDouble(args[i+1]);

		r = inf.doubleValue();
	    }

	    if ("-time".equals(args[i]))
	    {
		Integer time = Integer.parseInt(args[i+1]);

		t = time.intValue();
	    }
	}

	for (int i = 0; i < t; i++)
	{
	    System.out.println("Time: "+i);

	    y = a * (1+r);
	    
	    System.out.println("Infected: "+y);

	    a = y;

	    if (p != 0)
	    {
		if (a > p)
		{
		    System.out.println("It took "+i+" iterations to saturate the population");
		    System.exit(0);
		}
	    }
	}
    }
}
