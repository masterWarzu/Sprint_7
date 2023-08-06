package ru.yandex.praktikum.scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.scooter.courier.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client
{
    private final String ROOT = "/orders";

    @Step("Create new order {order}")
    public ValidatableResponse create(Order order)
    {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ROOT)
                .then();
    }

    @Step("Get list order")
    public ValidatableResponse getDefaultOrder()
    {
        return given()
                .spec(getSpec())
                .when()
                .get(ROOT)
                .then();
    }
}
