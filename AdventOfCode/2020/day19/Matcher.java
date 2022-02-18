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
        String str = "";

        System.out.println("Got "+expandRule(ruleNumber));

        return null;
    }

    public String expandRule (int ruleNumber)
    {
        Rule ruleToMatch = _rules[ruleNumber];
        Vector<String> results = new Vector<String>();

        String str = "";

        //System.out.println("Looking at rule "+ruleToMatch);

        if (ruleToMatch.isCharacterRule())
            str = ""+ruleToMatch.getMatch();
        else
        {
            int[] leftRules = ruleToMatch.leftRules();

            str += " ( ";

            for (int i = 0; i < leftRules.length; i++)
            {
                str += expandRule(leftRules[i]);

                System.out.println("Expanded one left rule: "+str);
            }

            int[] rightRules = ruleToMatch.rightRules();

            // want to skip and then come back later?
            String temp = new String(str);

            results.add(temp);

            if (rightRules != null)
            {
                System.out.println("Have OR rule");

                str += " | ";

                for (int j = 0; j < rightRules.length; j++)
                {
                    str += expandRule(rightRules[j]);
                }
            }

            str += " ) ";

            System.out.println("At this stage have: "+str+" and "+results);
        }

        System.out.println("Done and returning: "+str);

        return str;
    }

    private Rule[] _rules;
    private boolean _debug;
}