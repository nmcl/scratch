public class Passport
{
    public static final String UNASSIGNED_STRING = "unassigned";

    public static final String CM = "cm";
    public static final String IN = "in";

    public static final String[] EYE_COLOURS = { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };

    public Passport ()
    {
    }

    // byr (Birth Year) - four digits; at least 1920 and at most 2002.

    public final void setBirthYear (String byr)
    {
        if (byr.length() == 4)
        {
            int year = -1;

            try
            {
                year = Integer.valueOf(byr);

                if ((year >= 1920) && (year <= 2002))
                    _byr = byr;
            }
            catch (Exception ex)
            {
            }
        }
    }

    public final String getBirthYear ()
    {
        return _byr;
    }

    // iyr (Issue Year) - four digits; at least 2010 and at most 2020.

    public final void setIssueYear (String iyr)
    {
        if (iyr.length() == 4)
        {
            int year = -1;

            try
            {
                year = Integer.valueOf(iyr);

                if ((year >= 2010) && (year <= 2020))
                    _iyr = iyr;
            }
            catch (Exception ex)
            {
            }
        }
    }

    public final String getIssueYear ()
    {
        return _iyr;
    }

    // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.

    public final void setExpirationYear (String eyr)
    {
        if (eyr.length() == 4)
        {
            int year = -1;

            try
            {
                year = Integer.valueOf(eyr);

                if ((year >= 2020) && (year <= 2030))
                    _eyr = eyr;
            }
            catch (Exception ex)
            {
            }
        }
    }

    public final String getExpirationYear ()
    {
        return _eyr;
    }

    /*
     * hgt (Height) - a number followed by either cm or in:
     * - If cm, the number must be at least 150 and at most 193.
     * - If in, the number must be at least 59 and at most 76.
     */

    public final void setHeight (String hgt)
    {
        int units = hgt.indexOf(CM);
        boolean cm = false;

        if (units == -1)
            units = hgt.indexOf(IN);
        else
            cm = true;

        if (units != -1)
        {
            int size = Integer.valueOf(hgt.substring(0, units));
            boolean valid = false;

            if (cm)
            {
                if ((size >= 150) && (size <= 193))
                    valid = true;
            }
            else
            {
                if ((size >= 59) && (size <= 76))
                    valid = true;
            }

            if (valid)
                _hgt = hgt;
        }
    }

    public final String getHeight ()
    {
        return _hgt;
    }

    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.

    public final void setHairColour (String hcl)
    {
        if (hcl.length() == 7)
        {
            if (hcl.charAt(0) == '#')
            {
                if (hcl.substring(1).matches("^[0-9a-fA-F]+$"))
                    _hcl = hcl;
            }
        }
    }

    public final String getHairColour ()
    {
        return _hcl;
    }

    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.

    public final void setEyeColour (String ecl)
    {
        boolean found = false;

        for (int i = 0; (i < EYE_COLOURS.length) && (!found); i++)
        {
            if (EYE_COLOURS[i].equals(ecl))
                found = true;

        }

        if (found)
            _ecl = ecl;
    }

    public final String getEyeColour ()
    {
        return _ecl;
    }

    // pid (Passport ID) - a nine-digit number, including leading zeroes.

    public final void setPassportID (String pid)
    {
        if (pid.length() == 9)
        {
            try
            {
                Long value = Long.valueOf(pid);

                _pid = pid;
            }
            catch (Exception ex)
            {
            }
        }
    }

    public final String getPassportUD ()
    {
        return _pid;
    }

    // cid (Country ID) - ignored, missing or not.
    
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