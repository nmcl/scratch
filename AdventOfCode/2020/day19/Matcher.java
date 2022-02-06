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

        return null;
    }

    private String getMatchingString (Rule theRule, String current)
    {
        String str = current;

        if (theRule.getMatch() == Rule.NO_MATCH)
        {

        }
        else
            str += theRule.getMatch();

        return str;
    }

    private Rule[] _rules;
    private boolean _debug;
}