package Lesson8;

import lombok.SneakyThrows;
import java.sql.*;

public class DbConnection {

    private Connection connection;
    private Statement statement;

    public DbConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://@localhost:3306/weather_data", "root", "root");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS weather" +
                    "(" +
                    "city TEXT NOT NULL," +
                    "localDate TEXT NOT NULL," +
                    "weatherText TEXT NOT NULL," +
                    "temperature DOUBLE NOT NULL" +
                    ");");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @SneakyThrows
    public void insertValue(String city, String localDate, String weatherText, double temperature) {

        try (PreparedStatement prepInsert =
                     connection.prepareStatement("INSERT INTO weather (city,localDate, weatherText, temperature) VALUES(?, ?, ?, ?)")) {

            prepInsert.setString(1, city);
            prepInsert.setString(2, localDate);
            prepInsert.setString(3, weatherText);
            prepInsert.setDouble(4, temperature);
            prepInsert.addBatch();

            int[] result = prepInsert.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getValue(String column, String field, double equals) throws NullPointerException {

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select " + column + " from weather where " + field + " = " + equals);

            while (resultSet.next()){
                return resultSet.getString(column);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new NullPointerException();

    }

}
