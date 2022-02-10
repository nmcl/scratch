import java.util.*;

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
        Vector<Message> toMatch = new Vector<Message>();

        //if (_debug)
            System.out.println("Trying to match rule: "+ruleToMatch);

        String current = getMatchingString(ruleToMatch, "");

        System.out.println("Match using first rules: "+current);

        toMatch.add(new Message(current));

        return null;
    }

    // try first rule set only.
    
    private String getMatchingString (Rule theRule, String current)
    {
        String str = current;

        System.out.println("Looking to match "+theRule.getNumber());

        if (!theRule.isCharacterRule())
        {
            int[] firstRules = theRule.firstDependantRules();

            for (int i = 0; i < firstRules.length; i++)
            {
                str = getMatchingString(_rules[firstRules[i]], str);

                //if (_debug)
                    System.out.println("First rules string: "+str);
            }
        }
        else
            str += theRule.getMatch();

        System.out.println("\n**returning "+str);

        return str;
    }

    private Rule[] _rules;
    private boolean _debug;
}