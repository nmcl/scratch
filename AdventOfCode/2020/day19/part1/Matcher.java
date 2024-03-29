import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Matcher
{
    public Matcher (Rule[] rules, boolean debug)
    {
        _rules = rules;
        _debug = debug;
    }

    public long numberOfMatchingMessages (int ruleNumber, Message[] messages)
    {
        Pattern p = Pattern.compile(getRegexForRule(ruleNumber));
        Vector<String> messageContent = new Vector<String>();

        for (int i = 0; i < messages.length; i++)
            messageContent.add(messages[i].getContent());

        return messageContent.stream().filter(s -> p.matcher(s).matches()).count();
    }

    public String expandRule (int ruleNumber)
    {
        Rule ruleToMatch = _rules[ruleNumber];
        String str = "";

        if (_debug)
            System.out.println("Looking at rule "+ruleToMatch);

        if (ruleToMatch.isCharacterRule())
            str = ""+ruleToMatch.getMatch();
        else
        {
            int[] leftRules = ruleToMatch.leftRules();

            str += " ( ";

            for (int i = 0; i < leftRules.length; i++)
            {
                str += expandRule(leftRules[i]);
            }

            int[] rightRules = ruleToMatch.rightRules();

            if (rightRules != null)
            {
                str += " | ";

                for (int j = 0; j < rightRules.length; j++)
                {
                    str += expandRule(rightRules[j]);
                }
            }

            str += " ) ";
        }

        return str;
    }

    private final String getRegexForRule (int ruleNumber)
    {
        Rule theRule = _rules[ruleNumber];

        if (theRule.isCharacterRule())
            return ""+theRule.getMatch();
        else
        {
            if (!theRule.hasOrRule())
                return String.format("(?:%s)", ruleListToRegex(theRule.leftRules()));
            else
                return String.format("(?:%s|%s)", ruleListToRegex(theRule.leftRules()), ruleListToRegex(theRule.rightRules()));
        }
    }

    private final String ruleListToRegex (int[] rules)
    {
        Vector<Integer> v = new Vector<Integer>();  // convert to stream for this bit

        for (int i = 0; i < rules.length; i++)
            v.add(rules[i]);

        return v.stream().map(this::getRegexForRule).collect(Collectors.joining());
    }

    private Rule[] _rules;
    private boolean _debug;
}