package hivejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Register Driver and create Driver instance
        Class.forName(driverName);
        // get connection

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/emp1", "anees", "");
        System.out.println("connection established"+con);


        String query="CREATE TABLE employee2(id int,name string,age int)" +
                     "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','";

        PreparedStatement statement=con.prepareStatement(query);
        statement.execute();


        String path="/home/anees/name.txt";
        String query1="LOAD DATA LOCAL INPATH'" +
                      path+
                      "'INTO TABLE employee2";

        PreparedStatement statement1=con.prepareStatement(query1);
        statement1.execute();

        con.close();







    }}
