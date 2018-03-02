/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Cat.StringData;
import model.Cat.StringDataList;

/**
 *
 * @author David 
 */
public class CatView {
    
        //Formats each property of the record into StringData object
        public static StringData extractCat(ResultSet results) {
        StringData cat = new StringData();
        try {
            cat.cat_id = FormatUtils.formatInteger(results.getObject("cat_id"));
            cat.cat_type = FormatUtils.formatString(results.getObject("cat_type"));
            cat.cat_age = FormatUtils.formatInteger(results.getObject("cat_age"));
            cat.cat_mood = FormatUtils.formatString(results.getObject("cat_mood"));
            cat.catimg_url = FormatUtils.formatString(results.getObject("catimg_url"));
        } catch (Exception e) {
            cat.errorMsg = "Data Exception thrown in CatView.extractPlanet(): " + e.getMessage();
            System.out.println("*****" + cat.errorMsg);
        }
        return cat;
    }

    //Returns back an arraylist of StringData cat objects from the sql query.
    public static StringDataList buildCatlist(DbConn dbc) {

        StringDataList catList = new StringDataList();

        catList.dbError = dbc.getErr();
        if (catList.dbError.length() == 0) {
            
            String sql = "SELECT cat_type, cat_id, cat_age, cat_mood, catimg_url " +
                         "FROM sp17_3344_1_tue59286.catdata ORDER BY cat_type;";
            try {
                PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    catList.add(extractCat(results));
                }
            } catch (Exception e) {
                catList.dbError = "SQL Exception thrown in PlanetView.BuildPlanetList(): " + e.getMessage();
                System.out.println("*****" + catList.dbError);
            }
        }
        return catList;
    }
    
    
    public static StringData findCatByID(DbConn dbc, String id) {

        StringData cat = new StringData();

        if (id == null) {
            cat.errorMsg = "Cannot find cat with null id.";
            return cat;
        }

        cat.errorMsg = dbc.getErr();
        if (cat.errorMsg.length() == 0) {

            String sql = "SELECT cat_type, cat_id, cat_age, cat_mood, catimg_url " +
                         "FROM sp17_3344_1_tue59286.catdata WHERE cat_id = ?";

            try {
                PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
                stmt.setString(1, id);
                ResultSet results = stmt.executeQuery();

                if (results.next()) {
                    cat = extractCat(results);
                }
            } catch (Exception e) {
                cat.errorMsg = "SQL Exception thrown in CatView.findCatById(): " + e.getMessage();
                System.out.println("*****" + cat.errorMsg);
            }
        }
        return cat;
    }
}
