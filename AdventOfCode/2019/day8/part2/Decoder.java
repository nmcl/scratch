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

            Layer finalLayer = theImage.getRenderedLayer();
            
            System.out.println("Rendered layer:\n"+finalLayer.readableForm());
        }
    }
}