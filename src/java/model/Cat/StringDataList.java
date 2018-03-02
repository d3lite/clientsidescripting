package model.Cat;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StringDataList {

    public String dbError = "";
    public String loggedOn = "";
    private ArrayList<StringData> recordList = new ArrayList();

    // Default constructor just leaves the 2 data members initialized as above
    public StringDataList() {
    }

    // overloaded constructor populates the list (and possibly the dbError) //filters on user input
    public StringDataList(String catNameStartsWith, DbConn dbc) {

        StringData sd = new StringData();

        System.out.println("Searching for planets that start with " + catNameStartsWith);

        try {

            String sql = "SELECT cat_type, cat_id, cat_age, cat_mood,  catimg_url " +
                         "FROM sp17_3344_1_tue59286.catdata " +
                         "WHERE cat_type LIKE ? ORDER BY cat_type;";

            PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
            stmt.setString(1, catNameStartsWith + "%");
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                try {
                    sd = new StringData();
                    sd.cat_id = FormatUtils.formatInteger(results.getObject("cat_id"));
                    sd.cat_type = FormatUtils.formatString(results.getObject("cat_type"));
                    sd.cat_age = FormatUtils.formatDouble(results.getObject("cat_age"));
                    sd.cat_mood = FormatUtils.formatDate(results.getObject("cat_mood"));
                    sd.catimg_url = FormatUtils.formatString(results.getObject("catimg_url"));
                    this.recordList.add(sd);
                } catch (Exception e) {
                    sd.errorMsg = "Record Level Error in model.Cat.StringDataList Constructor: " + e.getMessage();
                    this.recordList.add(sd);
                }
            } // while
        } catch (Exception e) {
            this.dbError = "List Level Error in model.Cat.StringDataList Constructor: " + e.getMessage();
        }
    } // method
    
    
    //method to add StringData of planet ot arraylist
    public void add(StringData stringData) {
        this.recordList.add(stringData);
    }

} // class
