public class Matcher
{
    public Matcher (boolean debug)
    {
        _debug = debug;
    }

    public Message[] matchRule (int ruleNumber, Rule[] rules, Message[] messages)
    {
        Rule ruleToMatch = rules[ruleNumber];
        
        return null;
    }

    private boolean _debug;
}