public class Questions
{
    public final int NUMBER_OF_QUESTIONS = 26;

    public Questions ()
    {
        _answers = new boolean[NUMBER_OF_QUESTIONS];

        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
        {
            _answers[i] = false;
        }
    }

    private boolean[] _answers;
}