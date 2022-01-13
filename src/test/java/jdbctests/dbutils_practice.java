    //java altinda utilities package olustur

    package jdbctests;

    import org.testng.annotations.Test;
    import utilities.DBUtils;

    import java.util.List;
    import java.util.Map;

    public class dbutils_practice {

        //////////////////dynamic_list.java kodundaki isleri hazir kodlarla yapiyoruz

        @Test
        public void test1(){
            //create connection
            DBUtils.createConnection();

        //    DBUtils.getQueryResultMap("select * from departments");             //alt + enter
            List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap("select * from departments");

            for (Map<String, Object> map : queryResult) {
                System.out.println(map.toString());
            }

            //close connection
            DBUtils.destroy();
        }



        ///TASK : Sadece bir row bilgisini al. Sadece bir row varsa liste gerek yok, sadece mapa koyariz

        @Test
        public void test2(){
            //create connection
            DBUtils.createConnection();

        //    DBUtils.getQueryResultMap("select * from departments");             //alt + enter
            Map<String, Object> rowMap = DBUtils.getRowMap("Select first_name, last_name, salary, job_id from employees\n" + "where employee_id = 100");

                System.out.println(rowMap.toString());

            //close connection
            DBUtils.destroy();
        }



    }



