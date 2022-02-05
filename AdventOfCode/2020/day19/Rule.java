public class Rule
{
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

        _match = ' ';
    }

    public Rule (String number, char match)
    {
        _number = Integer.valueOf(number);

        _ruleOne = _ruleTwo = null;

        _match = match;
    }

    public final int getNumber ()
    {
        return _number;
    }

    @Override
    public String toString ()
    {
        return "Rule < "+_number+", "+_ruleOne+", "+_ruleTwo+", "+_match+" >";
    }

    private int _number;
    private int[] _ruleOne;
    private int[] _ruleTwo;
    private char _match;
}