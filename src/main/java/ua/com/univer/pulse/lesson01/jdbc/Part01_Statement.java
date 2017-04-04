package ua.com.univer.pulse.lesson01.jdbc;

import java.sql.*;

/**
 * Created by IShklyar on 04.04.2017.
 */
public class Part01_Statement {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Не обязательно загрузить байт кода драйвера в память. Это делается автоматически
        // Мы сделали для наглядности
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://127.0.0.1:5432/myjavadb";
        Connection con = DriverManager.getConnection(url, "postgres", "postgres");

        if (con == null) return;

        Statement st = con.createStatement();
        String sql = "insert into clients(name, lastname) values ('vasya', 'pupkin')";
        st.execute(sql);

        String selectSql = "select * from clients";
        ResultSet rs = st.executeQuery(selectSql);
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("lastname"));
        }
        st.close();

        con.close();
    }
}
