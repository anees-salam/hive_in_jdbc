package hivejdbc;

import java.sql.*;

public class FetchData {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Register Driver and create Driver instance
        Class.forName(driverName);

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/emp1", "anees", "");
        System.out.println("connection established"+con);


        String query="SELECT * FROM employee";

        PreparedStatement statement=con.prepareStatement(query);
        ResultSet output=statement.executeQuery();

        System.out.println("ID\tAGE\tNAME");
        while(output.next()){

            String name=output.getString("name");
            int age=output.getInt("age");
            int id =output.getInt("id");

            System.out.println(id+"\t"+age+"\t"+name);
        }

        con.close();

    }}
