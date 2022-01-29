import java.util.*;

public class MathsParser
{
    public MathsParser (boolean debug)
    {
        _debug = debug;
    }

    /**
     * All numbers within expressions are single digit so we can continue to use them as
     * characters.
     * 
     * Handle different precedence  to part 1.
     */

    public long parse (Vector<String> data)
    {
        long finalResult = 0L;

        /*
         * Since + has precedence, we need to unwind the entire
         * equation and compute those values first before doing *
         */

        for (int i = 0; i < data.size(); i++)
        {
            String currentLine = data.elementAt(i);
            char[] lineArray = currentLine.trim().replaceAll("\\s", "").toCharArray(); // remove all spaces
            Vector<Character> unrolled = new Vector<Character>();
            Stack<Character> nested = new Stack<Character>();

            for (int j = 0; j < lineArray.length; j++)
            {
                switch (lineArray[j])
                {
                    case Tokens.OPEN_BRACE:
                    {
                        nested.push(Tokens.OPEN_BRACE);
                    }
                    break;
                    case Tokens.CLOSE_BRACE:
                    {
                        boolean done = false;

                        while (!done && !nested.isEmpty())
                        {
                            Character c = nested.pop();

                            if (c != Tokens.OPEN_BRACE)
                                unrolled.add(c);
                            else
                                done = true;
                        }

                        nested.pop();
                    }
                    break;
                    case Tokens.PLUS:
                    {
                        System.out.println("nested "+nested);
                        System.out.println("unrolled "+unrolled);

                        System.out.println("character "+Tokens.PLUS);

                        while (!nested.isEmpty() && nested.peek() != Tokens.OPEN_BRACE)
                        {
                            System.out.println("peeked "+nested.peek());
          
                            if (nested.peek() == Tokens.PLUS)
                            {
                                System.out.println("adding "+nested.peek());

                                unrolled.add(nested.pop());
                                continue;
                            }

                            break;
                        }

                        nested.push(Tokens.PLUS);

                        System.out.println("nested now "+nested);
                    }
                    break;
                    case Tokens.MULTIPLY:
                    {
                        System.out.println("nested "+nested);
                        System.out.println("unrolled "+unrolled);

                        System.out.println("character "+Tokens.MULTIPLY);

                        while (!nested.isEmpty() && nested.peek() != Tokens.OPEN_BRACE)
                        {
                            System.out.println("peeked "+nested.peek());
          
                            if ((nested.peek() == Tokens.PLUS) || (nested.peek() == Tokens.MULTIPLY))
                            {
                                System.out.println("adding "+nested.peek());

                                unrolled.add(nested.pop());
                                continue;
                            }

                            break;
                        }

                        nested.push(Tokens.MULTIPLY);

                        System.out.println("nested now "+nested);
                    }
                    break;
                    default:
                    {
                        unrolled.add(lineArray[j]);
                    }
                    break;
                }
            }

            while (!nested.empty())
            {
                unrolled.add(nested.pop());
            }

            System.out.println("got "+unrolled);

            Stack<Long> values = new Stack<Long>();
      
            for (int k = 0; k < unrolled.size(); k++)
            {
                Character c = unrolled.elementAt(k);

                switch (c)
                {
                    case Tokens.PLUS:
                    {
                        values.push(values.pop() + values.pop());
                    }
                    break;
                    case Tokens.MULTIPLY:
                    {
                        values.push(values.pop() * values.pop());
                    }
                    break;
                    default:
                    {
                        values.push((long) Character.getNumericValue(c));
                    }
                    break;
                }
            }
      
            finalResult += values.pop();
        }

        return finalResult;
    }

    private boolean _debug;
}