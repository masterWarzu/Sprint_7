package ru.yandex.praktikum.scooter.courier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddCourierTest
{
    Courier courier;
    CourierClient courierClient;
    private int courierId;

    @Before
    public void setup()
    {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void out()
    {
        if(courierId != 0)
            courierClient.delete(courierId);
    }

    @Test
    public void createValidCourierTest()
    {
        boolean isOk = courierClient.createCourier(courier).extract().path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds).extract().path("id");
        assertTrue(isOk);
        assertNotEquals(0, courierId);
    }

//**********************************************************************************************************************
    @Test
    public void createWithoutLoginTest()
    {
        courier = Courier.getWithoutLogin();
        String message = courierClient.createCourier(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    public void createWithoutPasswordTest()
    {
        courier = Courier.getWithoutPassword();
        String message = courierClient.createCourier(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }
//**********************************************************************************************************************

    @Test
    public void createSameCourierTest()
    {
        CourierCredentials creds = CourierCredentials.from(courier);
        String doubleLogin = courierClient.createCourier(courier).extract().path("login");
        String message = courierClient.createCourier(courier).extract().path("message");
        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
        int code = courierClient.createCourier(courier).extract().path("code");
        assertEquals(409, code);
    }
}
