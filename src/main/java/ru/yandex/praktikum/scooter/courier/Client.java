package ru.yandex.praktikum.scooter.courier;

import ru.yandex.praktikum.scooter.config.Config;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class Client
{
    protected RequestSpecification getSpec()
    {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(Config.BASE_URL);
    }
}
