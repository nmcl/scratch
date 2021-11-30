public class Passport
{
    public static final String UNASSIGNED_STRING = "unassigned";

    public Passport ()
    {
    }

    public final void setBirthYear (String byr)
    {
        _byr = byr;
    }

    public final String getBirthYear ()
    {
        return _byr;
    }

    public final void setIssueYear (String iyr)
    {
        _iyr = iyr;
    }

    public final String getIssueYear ()
    {
        return _iyr;
    }

    public final void setExpirationYear (String eyr)
    {
        _eyr = eyr;
    }

    public final String getExpirationYear ()
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

    public final void setPassportID (String pid)
    {
        _pid = pid;
    }

    public final String getPassportUD ()
    {
        return _pid;
    }

    public final void setCountryID (String cid)
    {
        _cid = cid;
    }

    public final String getCountryID ()
    {
        return _cid;
    }

    public final boolean isValid ()
    {
        if ((_byr != UNASSIGNED_STRING) && (_iyr != UNASSIGNED_STRING))
        {
            if ((_eyr != UNASSIGNED_STRING) && (_pid != UNASSIGNED_STRING))
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

    private String _byr = UNASSIGNED_STRING;
    private String _iyr = UNASSIGNED_STRING;
    private String _eyr = UNASSIGNED_STRING;
    private String _hgt = UNASSIGNED_STRING;
    private String _hcl = UNASSIGNED_STRING;
    private String _ecl = UNASSIGNED_STRING;
    private String _pid = UNASSIGNED_STRING;
    private String _cid = UNASSIGNED_STRING;
}