package ru.yandex.praktikum.scooter.courier;

import io.restassured.response.ValidatableResponse;
import static org.apache.http.HttpStatus.*;

public class CourierClient extends Client
{
    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";

    public ValidatableResponse createCourier(Courier courier)
    {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

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
