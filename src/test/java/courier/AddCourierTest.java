package courier;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddCourierTest
{
    Courier courier;
    CourierClient courierClient;
    private int courierId;
    private String doubleLogin;

    @Before
    public void setup()
    {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @Test
    public void createValidCourierTest()
    {
        boolean isOk = courierClient.create(courier).extract().path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .extract().path("id");
        assertTrue(isOk);
        assertNotEquals(0, courierId);
        courierClient.delete(courierId);
    }

    @Test
    public void createWithoutPasswordTest()
    {
        courier = Courier.getWithoutPassword();
        String message = courierClient.createFailed(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    public void createWithoutLoginTest()
    {
        courier = Courier.getWithoutLogin();
        String message = courierClient.createFailed(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    public void createSameCourierTest()
    {
        CourierCredentials creds = CourierCredentials.from(courier);
        doubleLogin = courierClient.create(courier).extract().path("login");
        String message = courierClient.create(courier).extract().path("message");
        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
        int code = courierClient.create(courier).extract().path("code");
        assertEquals(409, code);
    }
}
