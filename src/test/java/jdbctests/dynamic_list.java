package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    //////////NEYI NERDEN ALIRIZ ?////////////////////////////////////////////////////
    //*** column name >> rsMetaData'dan,
    //*** Column value >> resultSet.getObject'ten aliriz
    //*** Number of rows >> while(resultSet.next()){}
    //*** Number of Column >> rsMetaData.getColumnCount()

    @Test
    public void MetaDataExample2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
   //   ResultSet resultSet = statement.executeQuery("Select first_name, last_name, salary, job_id from employees\n" + "where rownum <6");
        ResultSet resultSet = statement.executeQuery("Select * from countries");

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //List for keeping all rows a mep
        List<Map<String ,Object>> queryData = new ArrayList<>();

        int colCount = rsMetaData.getColumnCount();

        while(resultSet.next()){
            Map<String, Object> row = new HashMap<>();  //empty map

            for (int i = 1; i <= colCount; i++) {
                 row.put(rsMetaData.getColumnName(i), resultSet.getObject(i) );
            }

            queryData.add(row);                       //add your map to list

        }

        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}