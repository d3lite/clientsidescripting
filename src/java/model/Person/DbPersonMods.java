/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Person;

import model.Person.*;
import dbUtils.DbConn;
import dbUtils.PrepStatement;
import dbUtils.ValidationUtils;

/**
 *
 * @author David
 */
public class DbPersonMods {
    
    /**
     * input parameters:
     *
     * inputData: an object that holds all the pre-validated fields that the
     * user wants to insert into the database, such as email_address, ...,
     * credit_limit. Remember that all fields in inputData are String type (even
     * the dollar amount credit_limit) because this is PRE-VALIDATED data. dbc:
     * an open DbConn database connection object.
     *
     * output parameter:
     *
     * If record is updated OK, return "" empty string. Otherwise, return a form
     * level error message (e.g., if validation error, something like "please
     * try again", or could be database error, or could be a programmer error.
     */
    public static StringData insert(StringData userData, DbConn dbc) {

        StringData errorMsgs = new StringData();

        System.out.println("In InsertUpdate .insert() ready to insert planet with these values: " + userData.toString());

        errorMsgs = validate(userData);
        System.out.println("In InsertUpdate .insert()  finished with validation");

        String formMsg = "";

        if (errorMsgs.getCharacterCount() > 0) {  // at least one field has an error, don't go any further.
            System.out.println("Validation errors: " + errorMsgs.toString());
            errorMsgs.errorMsg = "Please try again";
            return errorMsgs;
        } else { // all fields passed validation
            System.out.println("In InsertUpdate DbPersonMods.insert() passed validation");

            // Start preparing SQL statement
            formMsg = dbc.getErr(); // will be empty string if DB connection is OK.
            if (formMsg.length() == 0) { // db connection is good

                // prepare the statement
                String sql = "INSERT INTO sp17_3344_1_tue59286.cat (person_email, person_password, person_nickname, person_rolename) "
                        + "VALUES (?, ?, ?, ?);";

                // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
                // Only difference is that Sally's class takes care of encoding null 
                // when necessary. And it also System.out.prints exception error messages.
                PrepStatement pStatement = new PrepStatement(dbc, sql);

                // Encoding string values into the prepared statement is pretty easy...
                pStatement.setString(1, userData.person_email);
                pStatement.setString(2, userData.person_password);
                pStatement.setString(3, userData.person_nickname);
                pStatement.setString(4, userData.person_rolename);

                System.out.println("ready to execute insert");

                // here the INSERT is actually executed
                int numRows = pStatement.executeUpdate();

                // This will return empty string if all went well, else all error messages.
                formMsg = pStatement.getErrorMsg();
                System.out.println("Error msg from after executing the insert: " + formMsg);

                if (formMsg.length() == 0) {
                    if (numRows == 1) {
                        formMsg = ""; // This means SUCCESS. Let the JSP page decide how to tell this to the user.
                    } else {
                        // probably never get here unless you forgot your WHERE clause and did a bulk sql update.
                        formMsg = numRows + " records were inserted when exactly 1 was expected.";
                    }
                    System.out.println("Number of records affected: " + numRows);
                }
            } // Db Connection is good - double check, JSP page should not send us a bad one... 
        } // customerId is not null and not empty string.
        errorMsgs.errorMsg = formMsg;
        return errorMsgs;
    } // constructor method
    
    private static StringData validate(StringData inputData) {

        StringData errorMsgs = new StringData();

        // Validation
        errorMsgs.person_email = ValidationUtils.stringValidationMsg(inputData.person_email, 45, true);    //true for only field required
        errorMsgs.person_password = ValidationUtils.stringValidationMsg(inputData.person_password, 45, true);
        errorMsgs.person_nickname = ValidationUtils.stringValidationMsg(inputData.person_nickname, 45, false);
        //errorMsgs.userRole = ValidationUtils.stringValidationMsg(inputData.userRole, 45, true);
        String memberType = inputData.person_rolename;
        if(memberType.equalsIgnoreCase("Member")){
            inputData.person_rolename = "Member";
        } else if(memberType.equalsIgnoreCase("Owner")){
            inputData.person_rolename = "Owner";
        } 
        else if(memberType.equalsIgnoreCase("Worker")){
            inputData.person_rolename = "Worker";
        } else {
            errorMsgs.person_rolename = " Please enter in Member or Admin.";
        }
        return errorMsgs;
    }
}
