package model.Person;

import model.Person.*;


public class StringData {

    // select country_id, country_name, flag_abbrev, flag_URL from country_flag
    public String person_id = "";
    public String person_email = "";
    public String person_password = "";
    public String person_nickname = "";
    public String person_rolename = "";
    public String errorMsg = "";
    
    // default constructor leaves all data members with empty string.
    public StringData() {

    }

    public int getCharacterCount() {
        String s = this.person_id + this.person_email + this.person_password 
                + this.person_nickname + this.person_rolename;
        return s.length();
    }

    public String toString() {
        return "person_id:" + this.person_id
                + ", person_email:" + this.person_email
                + ", person_password:" + this.person_password
                + ", person_nickname:" + this.person_nickname
                + ", person_rolename:" + this.person_rolename;
    }
}

