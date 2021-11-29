public class Passport
{
    public static final String UNASSIGNED_STRING = "unassigned";

    public static final int UNASSIGNED_INT = -1;

    public Passport ()
    {
    }

    public final void setBirthYear (int byr)
    {
        _byr = byr;
    }

    public final int getBirthYear ()
    {
        return _byr;
    }

    public final void setIssueYear (int iyr)
    {
        _iyr = iyr;
    }

    public final int getIssueYear ()
    {
        return _iyr;
    }

    public final void setExpirationYear (int eyr)
    {
        _eyr = eyr;
    }

    public final int getExpirationYear ()
    {
        return _eyr;
    }

    public final void setHeight (String hgt)
    {
        _hgt = hgt;
    }

    public final String getHeight ()
    {
        return _hgt;
    }

    public final void setHairColour (String hcl)
    {
        _hcl = hcl;
    }

    public final String getHairColour ()
    {
        return _hcl;
    }

    public final void setEyeColour (String ecl)
    {
        _ecl = ecl;
    }

    public final String getEyeColour ()
    {
        return _ecl;
    }

    public final void setPassportID (int pid)
    {
        _pid = pid;
    }

    public final int getPassportUD ()
    {
        return _pid;
    }

    public final void setCountryID (int cid)
    {
        _cid = cid;
    }

    public final int getCountryID ()
    {
        return _cid;
    }

    public final boolean isValid ()
    {
        if ((_byr != UNASSIGNED_INT) && (_iyr != UNASSIGNED_INT))
        {
            if ((_eyr != UNASSIGNED_INT) && (_pid != UNASSIGNED_INT))
            {
                if (!UNASSIGNED_STRING.equals(_hgt) && !UNASSIGNED_STRING.equals(_hcl) && !UNASSIGNED_STRING.equals(_ecl))
                    return true;
            }
        }

        return false;
    }

    @Override
    public String toString ()
    {
        return "Passport < byr:"+_byr+", iyr:"+_iyr+", eyr:"+_eyr+", hgt:"+_hgt+", hcl:"+_hcl+", ecl:"+_ecl+", pid:"+_pid+", cid:"+_cid+" >";
    }

    private int _byr = UNASSIGNED_INT;
    private int _iyr = UNASSIGNED_INT;
    private int _eyr = UNASSIGNED_INT;
    private String _hgt = UNASSIGNED_STRING;
    private String _hcl = UNASSIGNED_STRING;
    private String _ecl = UNASSIGNED_STRING;
    private int _pid = UNASSIGNED_INT;
    private int _cid = UNASSIGNED_INT;
}