public class PasswordData
{
    public PasswordData (PasswordPolicy policy, String password)
    {
        _policy = policy;
        _pwd = password;
    }

    public boolean valid ()
    {
        if ((_pwd.indexOf(_policy.letter()) != -1) && _policy.valid())
        {
            char[] asArray = _pwd.toCharArray();

            if ((asArray[_policy.first() -1] == _policy.letter()) && (asArray[_policy.second() -1] != _policy.letter()) ||
                (asArray[_policy.first() -1] != _policy.letter()) && (asArray[_policy.second() -1] == _policy.letter()))
            {
                return true;
            }
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