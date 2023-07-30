package orders;

import io.restassured.response.ValidatableResponse;

public class OrdersClient extends BaseOrders
{
    private final String ROOT = "/orders";
    private final String CANCEL = "/orders/cancel";

    public ValidatableResponse create(Orders orders)
    {
        return getSpec()
                .body(orders)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201);
    }

    public ValidatableResponse getList(Orders orders)
    {
        return getSpec()
                .body(orders)
                .when()
                .get(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    public ValidatableResponse delete(int track)
    {
        return getSpec()
                .pathParam("track", track)
                .when()
                .put(CANCEL)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
