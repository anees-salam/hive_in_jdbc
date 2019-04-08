package movie;

import java.sql.*;

public class FetchData {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Register Driver and create Driver instance
        Class.forName(driverName);

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/movie", "anees", "");
        System.out.println("connection established"+con);


        String query="SELECT item.movietitle, COUNT(data.rating) AS countrating " +
                " FROM data "+
                " JOIN item ON data.itemid=item.movieid "+
                " WHERE data.rating=5 "+
                " GROUP BY item.movietitle "+
                " ORDER BY countrating DESC "+
                " LIMIT 3";

        PreparedStatement statement=con.prepareStatement(query);
        ResultSet output=statement.executeQuery();

        System.out.println("MOVIE\tRATING");
        while(output.next()){

            String movie=output.getString(1);
            float count=output.getInt(2);

            System.out.println(movie+"\t"+count);
        }

        con.close();

    }}
