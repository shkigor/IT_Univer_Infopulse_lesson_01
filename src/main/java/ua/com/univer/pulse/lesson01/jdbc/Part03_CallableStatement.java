package ua.com.univer.pulse.lesson01.jdbc;

import java.sql.*;

/**
 * Created by IShklyar on 04.04.2017.
 */
public class Part03_CallableStatement {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Не обязательно загрузить байт кода драйвера в память. Это делается автоматически
        // Мы сделали для наглядности
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://127.0.0.1:5432/univerlesson01";
        Connection con = DriverManager.getConnection(url, "postgres", "postgres");

        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call getnamebyid(?,?)}");
        cs.setInt(1, 1);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        String name = cs.getString(2);
        System.out.println(name);

        cs.close();
        con.close();
    }
}
