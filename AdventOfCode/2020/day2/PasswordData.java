public class PasswordData
{
    public PasswordData (PasswordPolicy policy, String password)
    {
        _policy = policy;
        _pwd = password;
    }

    @Override
    public String toString ()
    {
        return _policy.toString()+"\n"+"Password: "+_pwd;
    }

    private PasswordPolicy _policy;
    private String _pwd;
}