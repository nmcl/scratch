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

        String current = getMatchingString(ruleToMatch, "", true);

        System.out.println("Match using first rules: "+current);

        toMatch.add(new Message(current));

        current = getMatchingString(ruleToMatch, "", false);

        toMatch.add(new Message(current));
        
        System.out.println("Match using second rules: "+current);

        return null;
    }

    private String getMatchingString (Rule theRule, String current, boolean checkFirst)
    {
        String str = current;

        System.out.println("Looking to match "+theRule.getNumber());

        if (!theRule.isCharacterRule())
        {
            if (checkFirst)
            {
                int[] firstRules = theRule.firstDependantRules();

                for (int i = 0; i < firstRules.length; i++)
                {
                    str = getMatchingString(_rules[firstRules[i]], str, true);

                    //if (_debug)
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

                        //if (_debug)
                            System.out.println("Second rules string: "+str);
                    }
                }
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