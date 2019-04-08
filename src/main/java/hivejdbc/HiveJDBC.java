package hivejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJDBC {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Register Driver and create Driver instance
        Class.forName(driverName);
        // get connection

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "anees", "");
        System.out.println("connection established"+con);


        Statement stm=con.createStatement();
        stm.execute("CREATE DATABASE emp5");
        System.out.println("database created");
        con.close();


}}
