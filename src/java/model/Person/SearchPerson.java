/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Person;

import model.Person.*;
import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author David
 */
public class SearchPerson {
    public static StringData logonFind(String userEmail, String password, DbConn dbc) {
        StringData retrievedData = new StringData();
        //Check that email & password are non-null
        if ((userEmail == null) || (password == null)) {
            retrievedData.errorMsg = "Search.logonFind: email and pw must be both non-null.";
            return retrievedData;
        }
        try {
            // prepare (compiles) the SQL statement
        String sql = "SELECT person_id, person_email, person_password, person_nickname, person_rolename " +
                      "FROM sp17_3344_1_tue59286.cat WHERE person_email = ? AND person_password = ?;";
           
            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, userEmail);
            pStatement.setString(2, password);

            ResultSet results = pStatement.executeQuery();
            // Just getting back the name and email
            if (results.next()) {
                retrievedData.person_email = userEmail;
                retrievedData.person_nickname = FormatUtils.formatString(results.getObject("person_nickname"));            
                return retrievedData;
            } else {
                return null;
            }
        } catch (Exception e) {
            retrievedData.errorMsg = "Exception in Search.logonFind: " + e.getMessage();
            System.out.println("******" + retrievedData.errorMsg);
            return retrievedData;
        }
    } // logonFind
}
