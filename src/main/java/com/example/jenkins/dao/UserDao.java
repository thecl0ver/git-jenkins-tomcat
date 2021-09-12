package com.example.jenkins.dao;

import com.example.jenkins.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;

public class UserDao implements Dao<User, Integer> {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }



    /*public static void createTable(){
        // строка sql запроса
        String createString = "CREATE TABLE IF NOT EXISTS tb_user (" +
                "id BIGSERIAL PRIMARY KEY," +
                "login VARCHAR(200) UNIQUE NOT NULL," +
                "name VARCHAR(100) NOT NULL," +
                "surname VARCHAR(100) NOT NULL," +
                "email VARCHAR(200) NOT NULL," +
                "age SMALLINT," +
                "creation_date TIMESTAMP NOT NULL," +
                "last_update_date TIMESTAMP," +
                "isdeleted BOOL NOT NULL DEFAULT FALSE" +
                ")";

        try {   // регистрация драйвера, загрузка класса
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }
        // import java.sql.*
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PASSWORD)) {
            try (Statement statement = connection.createStatement()){
                System.out.println(statement.executeUpdate(createString));
            }
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить запрос " + e.getMessage());
        }
    }*/


    @Override
    public void add(User entity) {
        String insertSql = "INSERT INTO tb_user(login, name, surname, age, email, creation_date) VALUES(?, ?, ?, ?, ?, ?)";

        if (entity.getLogin().equals(getByLogin(entity.getLogin()).getLogin())) {
            return;
        }

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setString(1, entity.getLogin());
                statement.setString(2, entity.getName());
                statement.setString(3, entity.getSurname());
                statement.setInt(4, entity.getAge());
                statement.setString(5, entity.getEmail());
                statement.setObject(6, LocalDateTime.now());
                statement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public HashSet<User> getAll() {
        String selectAll = "SELECT * FROM tb_user";
        HashSet<User> users = new HashSet<>();

        try (Statement statement = connection.createStatement()) {
            try (ResultSet result = statement.executeQuery(selectAll)) {
                while (result.next()) {
                    /*if (result.getBoolean("isdeleted")) {
                        continue;
                    }*/
                    User user = new User(
                            result.getLong("id"),
                            result.getString("login"),
                            result.getString("name"),
                            result.getString("surname"),
                            result.getInt("age"),
                            result.getString("email"),
                            result.getObject("creation_date", LocalDateTime.class),
                            result.getObject("last_update_date", LocalDateTime.class)
                    );

                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getByLogin(String login) {
        String selectUser = "SELECT * FROM tb_user WHERE login = ?";
        User user = new User();

        try (PreparedStatement statement = connection.prepareStatement(selectUser)) {
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setLogin(result.getString("login"));
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setAge(result.getInt("age"));
                user.setEmail(result.getString("email"));
                user.setCreationDate(result.getObject("creation_date", LocalDateTime.class));
                user.setLastUpdateDate(result.getObject("last_update_date", LocalDateTime.class));
            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public void update(User entity) {
        String updateSql = "UPDATE tb_user SET name = ?, surname = ?, age = ?, email = ?, last_update_date = ? WHERE login = ?";

        User user = getByLogin(entity.getLogin());
        if (user.isDeleted()) {
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setInt(3, entity.getAge());
            statement.setString(4, entity.getEmail());
            statement.setObject(5, LocalDateTime.now());
            statement.setString(6, entity.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByLogin(String login) {
        String deleteSql = "UPDATE tb_user SET isdeleted = ? WHERE login = ?";

        User user = getByLogin(login);
        if (user.isDeleted()) {
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setBoolean(1, true);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
