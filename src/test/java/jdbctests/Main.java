package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {              //bu throw exception olmali

     // String dbUrl = "jdbc:oracle:thin:@yourIPaddressandport:xe";
        String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:xe";
        String dbUsername ="hr";
        String dbPassword ="hr";

        //1. create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //2. cretaing statement object
        Statement statement = connection.createStatement();

        //3. run query and get the result in resultset object
//        ResultSet resultSet = statement.executeQuery("Select * from regions");


        //move pointer to the first row, ilk row a gitmek icin

//        System.out.println(resultSet.getString("region_name"));           //or column index number (1'den baslar) girebilirsin
//        System.out.println(resultSet.getString(2));
//        System.out.println(resultSet.getString("region_id") + " - " + resultSet.getString("region_name"));
//
//        resultSet.next();  //sonraki satir icin tekrar next demeliyiz
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getString(2));
//        System.out.println(resultSet.getString("region_id") + " - " + resultSet.getString("region_name"));
                                      //yukardakilerin yerine ;
//        while (resultSet.next()){               //boolean
//            System.out.println(resultSet.getString(1) + " - " + resultSet.getString("region_name"));
//        }

        //ikinci task
//        ResultSet resultSet1 = statement.executeQuery("Select first_name, last_name, salary from employees");
//
//        while (resultSet1.next()){               //boolean
//            System.out.println(resultSet1.getString(1) + " - " + resultSet1.getString(2) + " - " + resultSet1.getString(3));
//        }

        //3. task

        ResultSet resultSet = statement.executeQuery("Select * from departments");

        while (resultSet.next()){               //boolean
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - "
                    + resultSet.getInt(3) + " - " + resultSet.getInt(4));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();



    }
}













