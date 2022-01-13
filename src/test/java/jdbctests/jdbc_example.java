package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    ////////////////////////////////////ResultSet Methods/////////////////////////////
    //next()
    //previous()
    //beforeFirst()
    //afterLast()
    //getRow()
    //last()       >> move to last row
    //absolute()  >> go to spesific row
    //////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //asagidaki ilavelere dikkat
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //Task : how to find how many rows for the query available, Task : 2 nolu kolondakileri yazdir
        //go to last row, get the row count
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);  //27

        resultSet.beforeFirst();  //tekrar basa dönmek

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        /////////////////////////////////////ANOTHER EXAMPLE//////////////////////////
        resultSet = statement.executeQuery("Select * from regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    //////////////////////////META DATA////////////////////////////////////////////////////
    @Test
    public void MetaDataExample() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //get the data base related data inside the dbMetaData object

        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("User : " + dbMetaData.getUserName());
        System.out.println("Database Product Name  : " + dbMetaData.getDatabaseProductName());
        System.out.println("Database Product Version : " + dbMetaData.getDatabaseProductVersion());
        System.out.println("Driver Name : " + dbMetaData.getDriverName());
        System.out.println("Driver Version : " + dbMetaData.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //how many columns do we have ?
        int colCount = rsMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);

        //how to get column names ?
        //   System.out.println("rsMetaData.getColumnName(1) = " + rsMetaData.getColumnName(1));
        //   System.out.println("rsMetaData.getColumnName(2) = " + rsMetaData.getColumnName(2));

        //make this dynamic way :

        for (int i = 1; i <= colCount; i++) {
            System.out.println("rsMetaData.getColumnName(i) = " + rsMetaData.getColumnName(i));

        }




        resultSet.close();
        statement.close();
        connection.close();
    }
}

