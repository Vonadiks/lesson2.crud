import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //1 метод создани таблицы

    private static void createTable() throws SQLException {

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name  TEXT UNIQUE,\n" +
                "    count INTEGER,\n" +
                "    price INTEGER\n" +
                ");");
    }

    // 2 метод для добавления записи

    private static void insertData() throws SQLException {
        stmt.executeUpdate("INSERT INTO products (name, count, price) VALUES ('meat', 7, 40);");
    }

    //3 метод для получения записи

    private static void getData() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите наименование продукта, цену которого нужно узнать.");
        String result = scan.nextLine();
        String sql = String.format("SELECT price FROM products where name = '%s';", result);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("price"));
        }
    }

    // 4 метод для удаления записи(а очистки таблицы)

    private static void deleteOneRow() throws SQLException {
        stmt.executeUpdate("DELETE FROM products WHERE id = 1;");
    }

    private static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM products;");
    }

    // 5 удаление таблицы

    private static void dropTable() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS products;");
    }


    public static void main(String[] args) {
        try {
            connect();

//            createTable();
            insertData();
//            getData();
//            dropTable();
//            clearTable();
//            deleteOneRow();

            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
