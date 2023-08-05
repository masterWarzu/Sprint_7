package ru.yandex.praktikum.scooter.order;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.scooter.courier.Client;

import static io.restassured.RestAssured.*;

public class OrderClient extends Client
{
    private final String ROOT = "/orders";

    public ValidatableResponse create(Order order)
    {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ROOT)
                .then();
    }

    public ValidatableResponse getDefaultOrder()
    {
        return given()
                .spec(getSpec())
                .when()
                .get(ROOT)
                .then();
    }
}
