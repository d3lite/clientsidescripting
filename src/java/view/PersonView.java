package view;

// classes imported from java.sql.*
import model.Person.StringDataList;
import model.Person.StringData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// classes in my project
import dbUtils.*;

public class PersonView {

    public static StringData extractPerson(ResultSet results) {
        StringData person = new StringData();
        try {
            person.person_id = FormatUtils.formatInteger(results.getObject("person_id"));
            person.person_nickname = FormatUtils.formatString(results.getObject("person_nickname"));
            person.person_email = FormatUtils.formatString(results.getObject("person_email"));

              person.person_password = FormatUtils.formatString(results.getObject("person_password"));

             person.person_rolename = FormatUtils.formatString(results.getObject("person_rolename"));

        } catch (Exception e) {
            person.errorMsg = "Data Exception thrown in PersonView.extractPerson(): " + e.getMessage();
            System.out.println("*****" + person.errorMsg);
        }
        return person;
    }

    public static StringDataList buildPersonList(DbConn dbc) {

        StringDataList personList = new StringDataList();

        personList.dbError = dbc.getErr();
        if (personList.dbError.length() == 0) {

            String sql = "SELECT person_id, person_nickname, person_email, person_password, person_rolename "
                    + "FROM sp17_3344_1_tue59286.cat ORDER BY person_nickname";
            
            try {
                PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    personList.add(extractPerson(results));
                }
            } catch (Exception e) {
                personList.dbError = "SQL Excepption thrown in PersonView.BuildPersonList(): " + e.getMessage();
                System.out.println("*****" + personList.dbError);
            }
        }
        return personList;
    }

    public static StringData findPersonById(DbConn dbc, String id) {

        StringData person = new StringData();

        if (id == null) {
            person.errorMsg = "Cannot find person with null id.";
            return person;
        }

        person.errorMsg = dbc.getErr();
        if (person.errorMsg.length() == 0) {

            String sql = "SELECT person_id, person_nickname, person_email, person_password, person_rolename "
                    + "FROM sp17_3344_1_tue59286.cat WHERE person_id = ?";

            try {
                PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
                stmt.setString(1, id);
                ResultSet results = stmt.executeQuery();

                if (results.next()) {
                    person = extractPerson(results);
                }
            } catch (Exception e) {
                person.errorMsg = "SQL Exception thrown in PersonView.BuildPerson(): " + e.getMessage();
                System.out.println("*****" + person.errorMsg);
            }
        }
        return person;
    }

}
