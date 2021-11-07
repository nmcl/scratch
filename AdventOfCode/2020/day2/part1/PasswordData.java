public class PasswordData
{
    public PasswordData (PasswordPolicy policy, String password)
    {
        _policy = policy;
        _pwd = password;
    }

    public boolean valid ()
    {
        if (_pwd.indexOf(_policy.letter()) != -1)
        {
            char[] asArray = _pwd.toCharArray();
            int count = 0;

            for (int i = 0; i < asArray.length; i++)
            {
                if (asArray[i] == _policy.letter())
                    count++;
            }

            if ((count >= _policy.minumum()) && (count <= _policy.maximum()))
                return true;
        }

        return false;
    }

    @Override
    public String toString ()
    {
        return _policy.toString()+"\n"+"Password: "+_pwd;
    }

    private PasswordPolicy _policy;
    private String _pwd;
}