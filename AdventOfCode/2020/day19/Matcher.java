public class Matcher
{
    public Matcher (Rule[] rules, boolean debug)
    {
        _rules = rules;
        _debug = debug;
    }

    public Message[] matchRule (int ruleNumber, Message[] messages)
    {
        Rule ruleToMatch = _rules[ruleNumber];
        String current = "";

        getMatchingString(ruleToMatch, current, true);
        
        return null;
    }

    private String getMatchingString (Rule theRule, String current, boolean checkFirst)
    {
        String str = current;

        if (theRule.getMatch() == Rule.NO_MATCH)
        {
            if (checkFirst)
            {
                int[] firstRules = theRule.firstDependantRules();

                for (int i = 0; i < firstRules.length; i++)
                {
                    str = getMatchingString(_rules[firstRules[i]], str, true);

                    if (_debug)
                        System.out.println("First rules string: "+str);
                }
            }
            else
            {
                int[] secondRules = theRule.secondDependantRules();

                if (secondRules != null)
                {
                    for (int i = 0; i < secondRules.length; i++)
                    {
                        str = getMatchingString(_rules[secondRules[i]], str, false);

                        if (_debug)
                            System.out.println("Second rules string: "+str);
                    }
                }
            }
        }
        else
            str += theRule.getMatch();

        return str;
    }

    private Rule[] _rules;
    private boolean _debug;
}