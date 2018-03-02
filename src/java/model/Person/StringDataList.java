package model.Person;

import model.Person.*;
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

    
    
    
    //method to add StringData of cat ot arraylist
    public void add(StringData stringData) {
        this.recordList.add(stringData);
    }

} // class
