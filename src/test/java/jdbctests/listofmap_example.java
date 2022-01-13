package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {

        String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

    @Test
    public void MetaDataExample1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //List for keeping all rows a mep
        List<Map<String ,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");

        System.out.println("row1.toString() = " + row1.toString());

        Map<String, Object> row2 = new HashMap<>();   //>> ikinci kismin ne oldugunu bilmedigim icin, string or int, object yaptim
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochbar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");
        System.out.println("row2.toString() = " + row2.toString());

        System.out.println("Salary of Neena" + row2.get("salary"));

        //Adding rows to my empty list of map
        queryData.add(row1);
        queryData.add(row2);

        //get the stewen last name directly from the list (starting 0) >> get(0) row1 demek
        System.out.println(queryData.get(0).get("last_name"));

        resultSet.close();
        statement.close();
        connection.close();
    }
/////////////ayni islemleri dynamicly yapacagiz//////////////////////
    @Test
    public void MetaDataExample2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select first_name, last_name, salary, job_id from employees\n" + "where rownum <6");

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //List for keeping all rows a mep
        List<Map<String ,Object>> queryData = new ArrayList<>();

        resultSet.next();         //unutma
        Map<String, Object> row1 = new HashMap<>();
      //  row1.put("first_name", "Steven");
//        row1.put("last_name", "King");
//        row1.put("salary", 24000);
//        row1.put("job_id", "AD_PRES");

                            //////////NEYI NERDEN ALIRIZ ?////////////////////////////////////////////////////
                            //*** column name >> rsMetaData'dan,
                            //*** Column value >> resultSet.getObject'ten aliriz
                            //*** Number of rows >> while(resultSet.next()){}
                            //*** Number of Column >> rsMetaData.getColumnCount()

        row1.put(rsMetaData.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetaData.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetaData.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        resultSet.next();
        Map<String, Object> row2 = new HashMap<>();   //>> ikinci kismin ne oldugunu bilmedigim icin, string or int, object yaptim
//        row2.put("first_name", "Neena");
//        row2.put("last_name", "Kochbar");
//        row2.put("salary", 17000);
//        row2.put("job_id", "AD_VP");
        row2.put(rsMetaData.getColumnName(1), resultSet.getString(1));
        row2.put(rsMetaData.getColumnName(2), resultSet.getString(2));
        row2.put(rsMetaData.getColumnName(3), resultSet.getString(3));
        row2.put(rsMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println(row2.toString());

        System.out.println("Salary of Neena : " + row2.get("SALARY"));

        //Adding rows to my empty list of map
        queryData.add(row1);
        queryData.add(row2);

        //get the stewen last name directly from the list (starting 0) >> get(0) row1 demek
        System.out.println(queryData.get(1).get("LAST_NAME"));

        resultSet.close();
        statement.close();
        connection.close();
    }



}
