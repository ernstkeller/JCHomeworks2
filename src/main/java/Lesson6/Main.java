package Lesson6;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Main {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "http://dataservice.accuweather.com/";
        Response response = given()
                .when()
                .get("forecasts/v1/daily/5day/294021?apikey=%20%09M46xk221Oh4ArfuUUG140u1qG5BlyGs8%20&language=en");
        if(response.getStatusCode() == 200){
            System.out.println("Тест успешно пройден!");
        } else{
            System.out.println("Произошла ошибка!");
        }

    }
}
