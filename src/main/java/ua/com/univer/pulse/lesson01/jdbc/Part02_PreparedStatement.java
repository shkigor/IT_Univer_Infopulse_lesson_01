package ua.com.univer.pulse.lesson01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IShklyar on 04.04.2017.
 */
public class Part02_PreparedStatement {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Не обязательно загрузить байт кода драйвера в память. Это делается автоматически
        // Мы сделали для наглядности
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://127.0.0.1:5432/univerlesson01";
        Connection con = DriverManager.getConnection(url, "postgres", "postgres");

        if (con == null) return;

        PreparedStatement pstm = con.prepareStatement("insert into clients(name, lastname) values (? ,?)");
        pstm.setString(1, "Petya");
        pstm.setString(2, "Petrov");
        pstm.execute();
        pstm.close();

        con.close();
    }
}
