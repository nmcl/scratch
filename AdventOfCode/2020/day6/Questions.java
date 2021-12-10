public class Questions
{
    public static final int NUMBER_OF_QUESTIONS = 26;
    public static final int ASCII_A = 97;

    public Questions ()
    {
        _answers = new boolean[NUMBER_OF_QUESTIONS];

        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
        {
            _answers[i] = false;
        }
    }

    public final boolean answerQuestion (char number, boolean answer)
    {
        if ((number > 0) && (number <= NUMBER_OF_QUESTIONS))
        {
            _answers[number ASCII_A] = answer;

            return true;
        }

        return false;
    }

    private boolean[] _answers;
}