import java.io.*;
import java.util.*;

public class Decoder
{
    public static final int DEFAULT_WIDTH = 25;
    public static final int DEFAULT_HEIGHT = 6;

    public static final String IMAGE_FILE = "image.sif";
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-helps".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug] [-verify]");
                System.exit(0);
            }

            if ("-verify".equals(args[i]))
                verify = true;

            if ("-debug".equals(args[i]))
                debug = true;
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        BufferedReader reader = null;
        String line = null;

        try
        {
            reader = new BufferedReader(new FileReader(IMAGE_FILE));

            line = reader.readLine();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        if (line == null)
            System.out.println("Error in reading data from "+IMAGE_FILE);
        else
        {
            Image theImage = new Image(DEFAULT_WIDTH, DEFAULT_HEIGHT, line);

            if (debug)
            {
                System.out.println("Read: "+theImage.numberOfLayers());
                System.out.println("Image is: "+theImage.toString());
            }

            Vector<Layer> layers = theImage.getLayers();
            Enumeration<Layer> iter = layers.elements();
            Layer fewestZeroPixels = null;
            int zeros = Integer.MAX_VALUE;

            while (iter.hasMoreElements())
            {
                Layer theLayer = iter.nextElement();
                int numberOfZeros = theLayer.numberOfPixels(0);

                if (numberOfZeros <= zeros)
                {
                    fewestZeroPixels = theLayer;
                    zeros = numberOfZeros;
                }
            }

            if (debug)
                System.out.println("Layer with fewest zeros ("+zeros+"):\n"+fewestZeroPixels);

            int numberOfOnes = fewestZeroPixels.numberOfPixels(1);
            int numberOfTwos = fewestZeroPixels.numberOfPixels(2);

            System.out.println("Number of 1 digits multiplied by the number of 2: "+(numberOfOnes * numberOfTwos));
        }
    }
}