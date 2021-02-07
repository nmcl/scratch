import java.util.*;

public class GameEngine
{
    public GameEngine (Vector<String> instructions, boolean debug)
    {
        _computer = new Intcode(instructions, null, debug);
        _debug = debug;
        _theScreen = new Screen(_debug);
        _paddlePosition = null;
        _ballPosition = null;
        _stick = new Joystick();
    }

    public final boolean playGame ()
    {
        int[] output = null;
                
        /*
         * Initialise the screen. Run the game instructions
         * once.
         */

         // Replace entry 0 with 2 (unlimited lives)

        _computer.changeInstruction(0, "2");

        while (!_computer.hasHalted() && !_computer.waitingForInput())
        {
            System.out.println("**waiting for input "+_computer.waitingForInput());

            output = getOutput();

            if (output != null)
            {
                if (_debug)
                    System.out.println("Tile information: <"+output[0]+", "+output[1]+"> and "+TileId.idToString(output[2]));

                System.out.println("**initial "+output[0]+" "+output[1]+" "+output[2]);

                Tile theTile = new Tile(new Coordinate(output[0], output[1]), output[2]);

                _theScreen.updateTile(theTile);

                if (theTile.getId() == TileId.BALL)
                    _ballPosition = theTile.getPosition();
                else
                {
                    if (theTile.getId() == TileId.PADDLE)
                        _paddlePosition = theTile.getPosition();
                }
            }
            else
            {
                System.out.println("**Waiting for input!");
            }

            System.out.println("**computer status "+Status.toString(_computer.status()));
        }

        /*
         * Where is the joystick?
         */

        if (_ballPosition.getX() > _paddlePosition.getX())
            _stick.setPosition(Joystick.TILTED_RIGHT);
        else
        {
            if (_ballPosition.getX() < _paddlePosition.getX())
                _stick.setPosition(Joystick.TILTED_LEFT);
        }

        System.out.println("**initial joystick "+_stick);

        /*
         * Now let's play the game!
         */

        System.out.println("\n\nPLAYING");

        while (!_computer.hasHalted())
        {
            System.out.println("**COMPUTER "+_computer.hasHalted());

            _computer.setInput(Integer.toString(_stick.getPosition()));

            output = getOutput();

            System.out.println("**output "+output);
            
            if (_debug)
                System.out.println("Tile information: <"+output[0]+", "+output[1]+"> and "+TileId.idToString(output[2]));

            System.out.println("**gaming values "+output[0]+" "+output[1]+" "+output[2]);

            if ((output[0] == -1) && (output[1] == 0))
            {
                // update score

                _theScreen.getSegmentDisplay().setScore(output[2]);

                System.out.println("**score now "+_theScreen.getSegmentDisplay());
            }
            else
            {
                Tile theTile = new Tile(new Coordinate(output[0], output[1]), output[2]);

                _theScreen.updateTile(theTile);

                if(theTile.getId() == TileId.BALL)
                {
                    _ballPosition = theTile.getPosition();

                    if (_ballPosition.getX() > _paddlePosition.getX())
                        _stick.setPosition(Joystick.TILTED_RIGHT);
                    else
                    {
                        if (_ballPosition.getX() < _paddlePosition.getX())
                            _stick.setPosition(Joystick.TILTED_LEFT);
                        else
                            _stick.setPosition(Joystick.NEUTRAL_POSITION);
                    }
                }
                else
                {
                    if (theTile.getId() == TileId.PADDLE)
                        _paddlePosition = theTile.getPosition();
                }
            }

            System.out.println("**gaming joystick "+_stick);
        }

        System.out.println("**score "+_theScreen.getSegmentDisplay());

        return true;
    }

    public final int getNumberOfBlocks ()
    {
        return _theScreen.numberOfBlocks();
    }

    private final int[] getOutput ()
    {
        int[] values = new int[3];

        System.out.println("**got "+_computer.hasPaused()+" "+_computer.hasOutput());

        do 
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput() && !_computer.waitingForInput());

        if (_computer.waitingForInput())
            return null;

        System.out.println("**1 here "+Status.toString(_computer.status())+" "+_computer.hasOutput());

        values[0] = Integer.parseInt(_computer.getOutput());

        do
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput());

        System.out.println("**2 here "+Status.toString(_computer.status())+" "+_computer.hasOutput());

        values[1] = Integer.parseInt(_computer.getOutput());

        do
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput());

        System.out.println("**3 here "+Status.toString(_computer.status())+" "+_computer.hasOutput());

        values[2] = Integer.parseInt(_computer.getOutput());

        return values;
    }

    private Intcode _computer;
    private boolean _debug;
    private Screen _theScreen;
    private Coordinate _paddlePosition;
    private Coordinate _ballPosition;
    private Joystick _stick;
}