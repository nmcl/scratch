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

    public final int playGame ()
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
            output = getOutput();

            if (output != null)
            {
                if (_debug)
                    System.out.println("Tile information: <"+output[0]+", "+output[1]+"> and "+TileId.idToString(output[2]));

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
                if (_debug)
                    System.out.println("Waiting for input!");
            }
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

        /*
         * Now let's play the game!
         */

        while (!_computer.hasHalted())
        {
            _computer.setInput(Integer.toString(_stick.getPosition()));

            output = getOutput();

            if (output != null)
            {
                if (_debug)
                    System.out.println("Tile information: <"+output[0]+", "+output[1]+"> and "+TileId.idToString(output[2]));

                if ((output[0] == -1) && (output[1] == 0))
                {
                    // update score

                    _theScreen.getSegmentDisplay().setScore(output[2]);

                    if (_debug)
                        System.out.println("Score now "+_theScreen.getSegmentDisplay());
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
            }
        }

        return _theScreen.getSegmentDisplay().getScore();
    }

    public final int getNumberOfBlocks ()
    {
        return _theScreen.numberOfBlocks();
    }

    private final int[] getOutput ()
    {
        int[] values = new int[3];

        do 
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput() && !_computer.waitingForInput() && !_computer.hasHalted());

        if (_computer.waitingForInput() || _computer.hasHalted())
            return null;

        values[0] = Integer.parseInt(_computer.getOutput());

        do
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput());

        values[1] = Integer.parseInt(_computer.getOutput());

        do
        {
            _computer.singleStepExecution();
        } while (!_computer.hasPaused() && !_computer.hasOutput());
        
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