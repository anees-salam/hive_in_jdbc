package airport;

import java.sql.*;

public class question2 {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName(driverName);

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/airport", "anees", "");
        System.out.println("connection established"+con);


        String query="SELECT ar.dest,(ar.avg_arr+d.avg_dep) AS total_delay,n.name " +
                " FROM " +
                "( SELECT origin,AVG(dep_delay) AS avg_dep " +
                " FROM 2008_dynam a " +
                " WHERE a.month=1 " +
                " GROUP BY origin) d " +
                " JOIN " +
                " (SELECT dest,AVG(arr_delay) AS avg_arr " +
                " FROM 2008_dynam a " +
                " WHERE a.month=1 " +
                " GROUP BY dest) ar ON (d.origin=ar.dest) " +
                " JOIN airport_name n ON (n.code=ar.dest) " +
                " ORDER BY total_delay ";

        PreparedStatement statement=con.prepareStatement(query);
        ResultSet rs=statement.executeQuery();

        System.out.println("SHOWING DETAILS");
        while (rs.next()){
            String airport_code=rs.getString(1);
            float total_delay =rs.getFloat(2);
            String airport_name=rs.getString(3);

            System.out.println(airport_code+"   :   "+airport_name+"    :   "+total_delay);

        }

        con.close();

}}