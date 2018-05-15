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

        String url = "jdbc:postgresql://127.0.0.1:5432/univerlesson01";
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

        // Прокручиваемый ResultSet
        DatabaseMetaData dbmd = con.getMetaData();
        int JDBCVersion = dbmd.getJDBCMajorVersion();
        boolean srs = dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        if (JDBCVersion > 2 || srs == true)
        {
            // давай, прокручивай!
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // такой ResultSet допускает итерации назад и вперед, но если данные в базе данных изменятся, ResultSet не отразит этого. Пожалуй, этот тип прокручиваемого ResultSet ― наиболее востребованный;
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet scrollingRS = stmt.executeQuery(selectSql);
            if (scrollingRS.first()) { // next(), previous(), relative(), absolute()
                System.out.println("\nFirst ROW:");
                System.out.println(scrollingRS.getInt("id") + " " + scrollingRS.getString("name") + " " + scrollingRS.getString("lastname"));
            }
            if (scrollingRS.last()) {
                System.out.println("\nLast ROW:");
                System.out.println(scrollingRS.getInt("id") + " " + scrollingRS.getString("name") + " " + scrollingRS.getString("lastname"));
            }
        }

        st.close();

        con.close();
    }
}
