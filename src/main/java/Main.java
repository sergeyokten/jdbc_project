import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/",
                "root",
                "root");
        PreparedStatement statement = connection.prepareStatement("create database if not exists abcdef character set 'utf8'");
        statement.executeUpdate();
        statement.execute("use abcdef");
        statement.execute("create table if not exists users(id int primary key auto_increment , name varchar(45) )");
//        save(connection, "vasya");
//        save(connection, "petya");
//        save(connection, "tomat");

        findAll(connection).forEach(user -> System.out.println(user));


    }

    public static void save(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into users (name) values (?);");
        statement.setString(1, name);
        statement.executeUpdate();
    }

    public static List<User> findAll(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from users");
        ResultSet resultSet = statement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString("name");
            users.add(new User(id, name));
        }

        return users;

    }
}
