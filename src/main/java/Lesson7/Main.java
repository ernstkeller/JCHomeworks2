package Lesson7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Main {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "http://dataservice.accuweather.com/";
        Response response = given()
                .when()
                .get("forecasts/v1/daily/5day/294021?apikey=M46xk221Oh4ArfuUUG140u1qG5BlyGs8&language=en&metric=true");

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper
                .readTree(response.asPrettyString())
                .at("/DailyForecasts");

        List<DailyForecasts> dailyForecasts = new ArrayList<>();

        if(jsonNode.isArray()){
            for (JsonNode jsonNode1 : jsonNode){
                dailyForecasts.add(objectMapper.convertValue(jsonNode1, DailyForecasts.class));

            }

        }

        for (DailyForecasts dailyForecast : dailyForecasts) {
            System.out.println("В Москве " + dailyForecast.getDate() + " ожидается максимальная температура "
                    + dailyForecast.getTemperature().getMaximum().getValue() + " градусов Цельсия и минимальная температура "
                    + dailyForecast.getTemperature().getMinimum().getValue() + " градусов Цельсия");
        }



    }
}
