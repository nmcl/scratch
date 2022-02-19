public class Message
{
    public Message (String content)
    {
        _content = content;
    }

    public final String getContent ()
    {
        return _content;
    }

    @Override
    public String toString ()
    {
        return "Message: "+_content;
    }

    private String _content;
}