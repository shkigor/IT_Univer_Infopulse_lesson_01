package ua.com.univer.pulse.lesson01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IShklyar on 04.04.2017.
 */
public class Part04_Batch {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Не обязательно загрузить байт кода драйвера в память. Это делается автоматически
        // Мы сделали для наглядности
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://127.0.0.1:5432/myjavadb";
        Connection con = DriverManager.getConnection(url, "postgres", "postgres");

        if (con == null) return;

        Statement st = con.createStatement();
        st.addBatch("insert into clients(name, lastname) values ('Ivan', 'Ivanov')");
        st.addBatch("insert into clients(name, lastname) values ('Ivan1', 'Ivanov1')");
        int res[] = st.executeBatch();
        System.out.println(res[0]);
        System.out.println(res[1]);
        st.close();

        con.close();
    }
}
