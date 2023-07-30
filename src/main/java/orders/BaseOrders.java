package orders;

import config.Config;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseOrders
{
    protected RequestSpecification getSpec()
    {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(Config.BASE_URL);
    }
}
