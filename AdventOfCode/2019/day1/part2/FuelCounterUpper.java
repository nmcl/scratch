import java.io.*;

public class FuelCounterUpper
{
    public static void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-mass <mass>] [-verify]");
                System.exit(0);
            }

            if ("-mass".equals(args[i]))
            {
                System.out.println("Fuel required for a mass of "+args[i+1]+" is "+FuelCounterUpper.fuelRequired(Long.valueOf(args[i+1])));
                System.exit(0);
            }

            if ("-verify".equals(args[i]))
            {
                FuelCounterUpper.verify();
                System.exit(0);
            }
        }
        
        File file = new File("modules.txt");  // hard coded for now
        BufferedReader reader = null;
        long totalFuel = 0;
        long totalMass = 0;
        
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                totalMass += Long.valueOf(text);
                totalFuel += FuelCounterUpper.fuelRequired(Long.valueOf(text));
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ex)
            {
            }
        }

        System.out.println("Fuel required for a total mass of "+totalMass+" is "+totalFuel);
    }

    private static long fuelRequired (long mass)
    {
        long fuel = ((mass / 3) - 2);
        
        if (fuel > 0)
            return fuel + fuelRequired(fuel);
        else
            return 0;
    }

    private static void verify ()
    {
        if (fuelRequired(12) == 2)
        {
            if (fuelRequired(14) == 2)
            {
                if (fuelRequired(1969) == 966)
                {
                    if (fuelRequired(100756) == 50346)
                    {
                    System.out.println("Verified ok!");
                    }
                    else
                    System.out.println("100756 failed to verify!");
                }
                else
                    System.out.println("1969 failed to verify!");
            }
            else
                System.out.println("14 failed to verify!");
        }
        else
            System.out.println("12 failed to verify!");
    }
}
