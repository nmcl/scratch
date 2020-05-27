/**
 * Simulate viral growth through exponential formula.
 *
 * Dedicated to Elizabeth Little, born May 6th 1947, died May 12th 2020. Victim of coronavirus.
 */

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
	double mingle = 0.0; // a fudge factor to represent population mingling or not
	double isolate = 0.0; // a fudge factor to represent population that isolates
	int algorithm = 1;
	int population = 0;

	for (int i = 0; i < args.length; i++)
        {
	    if ("-help".equals(args[i]))
	    {
		System.out.println("growth [-infection <value>] [-time <value>] [-population <value>] [-mingle <value>] [-isolate <value>] <-algo <value>] [-help]");
		System.exit(0);
	    }

	    /**
	     * The act of mingling increases the chance of cross pollination
	     * of the infection, i.e., it increases the infection rate.
	     */

	    if ("-mingle".equals(args[i]))
	    {
		Double m = Double.parseDouble(args[i+1]);

		mingle = m.doubleValue();
	    }

	    /**
	     * The act of isolating reduces the changes of cross pollination.
	     */

	    if ("-isolate".equals(args[i]))
	    {
		Double iso = Double.parseDouble(args[i+1]);

		isolate = iso.doubleValue();
	    }

	    /**
	     * Set the population so we can determine after how many iterations the
	     * virus reaches saturation and we stop.
	     */

	    if ("-population".equals(args[i]))
	    {
		Integer pop = Integer.parseInt(args[i+1]);

		population = pop.intValue();
	    }

	    if ("-algo".equals(args[i]))
	    {
		Integer al = Integer.parseInt(args[i+1]);

		algorithm = al.intValue();
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

	/**
	 * What do we do with mingle and isolate? Since they both affect the infection rate
	 * we'll have different algorithms:
	 *
	 * (i) a simple alogorithm of adding mingle and subtracting isolate.
	 * (ii) mingle is a multiplies for r and isolate is a divisor.
	 */

	switch (algorithm)
	{
	case 1:
	    System.out.println("Algorithm 1.");
	    r = r + mingle - isolate;
	    break;
	case 2:
	    System.out.println("Algorithm 2.");
	    r = r*(mingle/isolate);
	    break;
	default:
	    System.out.println("Algorithm "+algorithm+" unknown.");
	    System.exit(0);
	}

	System.out.println("Time: 0\nInfected: "+a);

	for (int i = 1; i < t; i++)
	{
	    System.out.println("Time: "+i);

	    y = a * (1+r);
	    
	    System.out.println("Infected: "+y);

	    a = y;

	    if (population != 0)
	    {
		if (a > population)
		{
		    System.out.println("It took "+i+" iterations to saturate the population");
		    System.exit(0);
		}
	    }
	}
    }
}
