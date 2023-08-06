package ru.yandex.praktikum.scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.*;

public class CourierClient extends Client
{
    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";

    @Step("Create new courier {courier}")
    public ValidatableResponse createCourier(Courier courier)
    {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Login with courier {courier}")
    public ValidatableResponse login(CourierCredentials creds)
    {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK);
    }

    @Step("Login without login courier {courier}")
    public ValidatableResponse loginWithoutLogin(CourierCredsWithoutLogin creds)
    {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Login without password courier {courier}")
    public ValidatableResponse loginWithoutPass(CourierCredsWithoutPassword creds)
    {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Login without new courier {courier}")
    public ValidatableResponse loginWrongCreds(CourierWrongCreds creds)
    {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(SC_NOT_FOUND);
    }

    @Step("Delete courier {courier}")
    public void delete(int courierId)
    {
        String COURIER = "/courier/{courierId}";
        getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK);
    }
}
