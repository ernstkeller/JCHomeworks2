package Lesson8;

import Lesson7.DailyForecasts;

public class Main {

    public static void main(String[] args) {

        DbConnection dbConnection = new DbConnection();
        ParseResponse parseResponse = new ParseResponse();

        for (DailyForecasts dailyForecast: parseResponse.getDailyForecasts()) {

            dbConnection.insertValue("Moscow",
                    dailyForecast.getDate(),
                    dailyForecast.getDay().getIconPhrase(),
                    Double.parseDouble(dailyForecast.getTemperature().getMinimum().getValue()));

        }

        System.out.println(dbConnection.getValue("localDate","temperature", 3.0));

    }

}
