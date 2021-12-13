public class Answers
{
    public static final int NUMBER_OF_QUESTIONS = 26;
    public static final int ASCII_A = 97;

    public Answers ()
    {
        _answers = new boolean[NUMBER_OF_QUESTIONS];

        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++)
        {
            _answers[i] = false;
        }

        _numberOfPeople = 0;
    }

    public final boolean answerQuestion (char number)
    {
        return answerQuestion(number, true);
    }

    public final boolean answerQuestion (char number, boolean answer)
    {
        int index = number - ASCII_A;

        if ((index >= 0) && (index <= NUMBER_OF_QUESTIONS))
        {
            _answers[index] = answer;

            return true;
        }

        return false;
    }

    public final int numberOfTrueAnswers ()
    {
        int count = 0;

        for (int i = 0; i < _answers.length; i++)
        {
            if (_answers[i])
                count++;
        }

        return count;
    }

    public final void addPersonToGroup ()
    {
        _numberOfPeople++;
    }

    public final int numberOfPeopleInGroup ()
    {
        return _numberOfPeople;
    }

    private boolean[] _answers;
    private int _numberOfPeople;
}