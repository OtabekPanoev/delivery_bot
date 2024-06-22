package uz.pdp;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) {

//        select();

        insert();

    }

    private static void insert() {
        try {
            Connection con = getConnection();

            String sql = "insert into accounts(name, balance ) values(?,?), (?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "Sarvar");
            st.setDouble(2, 100.11);

            st.setString(3, "G'olib");
            st.setDouble(4, 200.1);

            int res = st.executeUpdate();
            System.out.println(res);

            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws Exception {

        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root123");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    private static void select() {
        try {
            Connection con = getConnection();

            String sql = "select * from accounts";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                System.out.println("id = " + id + " name = " + name + " balance = " + balance);
            }


            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
