public class Parser
{
    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public Questions work (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Plane thePlane = new Plane(debug);

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                Barcode b = new Barcode(line, debug);

                if (debug)
                    System.out.println("Loaded: "+b);

                thePlane.addSeat(b.getSeat());
            }
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
    }
    
    private boolean _debug;
}