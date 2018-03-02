package model.Cat;

public class StringData {

    // select country_id, country_name, flag_abbrev, flag_URL from country_flag
    public String   cat_id = "";
    public String cat_type = "";
    public String cat_age = "";
    public String cat_mood = "";
    
    public String catimg_url = "";
    public String errorMsg = "";
    
    // default constructor leaves all data members with empty string.
    public StringData() {

    }

    public int getCharacterCount() {
        String s = this.cat_id + this.cat_type + this.cat_age 
                + this.cat_mood + this.catimg_url;
        return s.length();
    }

    public String toString() {
        return "cat_id:" + this.cat_id
                + ", cat_type:" + this.cat_type
                + ", cat_age:" + this.cat_age
                + ", cat_mood:" + this.cat_mood
               
                + ", catimg_url:" + this.catimg_url;
    }
}

