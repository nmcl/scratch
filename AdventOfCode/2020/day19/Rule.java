public class Rule
{
    private static final char NO_MATCH = ' ';

    public Rule (String number, String[] ruleOneList, String[] ruleTwoList)
    {
        _number = Integer.valueOf(number);
        
        _ruleOne = new int[ruleOneList.length];

        for (int i = 0; i < _ruleOne.length; i++)
            _ruleOne[i] = Integer.valueOf(ruleOneList[i]);

        if (ruleTwoList != null)
        {
            _ruleTwo = new int[ruleTwoList.length];

            for (int j = 0; j < _ruleOne.length; j++)
                _ruleTwo[j] = Integer.valueOf(ruleTwoList[j]);
        }
        else
            _ruleTwo = null;

        _match = NO_MATCH;
    }

    public Rule (String number, char match)
    {
        _number = Integer.valueOf(number);

        _ruleOne = _ruleTwo = null;

        _match = match;
    }

    public final boolean isCharacterRule ()
    {
        if (_match == NO_MATCH)
            return false;
        else
            return true;
    }

    public final boolean hasOrRule ()
    {
        if (_ruleTwo != null)
            return true;
        else
            return false;
    }

    public final int getNumber ()
    {
        return _number;
    }

    public final int[] leftRules ()
    {
        return _ruleOne;
    }

    public final int[] rightRules ()
    {
        return _ruleTwo;
    }

    public final char getMatch ()
    {
        return _match;
    }

    @Override
    public String toString ()
    {
        String str = "Rule < "+_number+", ";

        if (_ruleOne != null)
        {
            for (int i = 0; i < _ruleOne.length; i++)
                str += _ruleOne[i]+" ";

            if (_ruleTwo != null)
            {
                str += "OR ";

                for (int i = 0; i < _ruleTwo.length; i++)
                    str += _ruleTwo[i]+" ";
            }

            str += ">";
        }
        else
            str += "'"+_match+"' >";

        return str;
    }

    private int _number;
    private int[] _ruleOne;
    private int[] _ruleTwo;
    private char _match;
}