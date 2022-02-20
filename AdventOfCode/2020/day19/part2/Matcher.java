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
        Vector<String> messageContent = new Vector<String>();

        for (int i = 0; i < messages.length; i++)
            messageContent.add(messages[i].getContent());

        String rule42Regex = getRegexForRule(42);
        String rule31Regex = getRegexForRule(31);
        String overallRegex = String.format("(%s+)(%s+)", rule42Regex, rule31Regex);

        Pattern rule42Pattern = Pattern.compile(rule42Regex);
        Pattern rule31Pattern = Pattern.compile(rule31Regex);
        Pattern fullPattern = Pattern.compile(overallRegex);

        return messageContent.stream()
                .filter(s -> matchesRules(s, fullPattern, rule42Pattern, rule31Pattern))
                .count();
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

    private final boolean matchesRules (String message, Pattern pattern, Pattern patternOne, Pattern patternTwo)
    {
        java.util.regex.Matcher m = pattern.matcher(message);

        if (m.matches())
            return numberOfMatches(patternOne, m.group(1)) > numberOfMatches(patternTwo, m.group(2));
        
        return false;
    }

    private int numberOfMatches (Pattern pattern, String toMatch)
    {
        int count = 0;
        java.util.regex.Matcher m = pattern.matcher(toMatch);

        while (m.find())
            count++;

        return count;
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