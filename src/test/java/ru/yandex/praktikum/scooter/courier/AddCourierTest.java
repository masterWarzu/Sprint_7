package ru.yandex.praktikum.scooter.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Create courier login")
    @Description("Проверка, что со всеми параметрами курьер создаётся")
    public void createValidCourierTest()
    {
        boolean isOk = courierClient.createCourier(courier).extract().path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds).extract().path("id");
        assertTrue(isOk);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check error when create courier without login")
    @Description("Проверка, что при попытке создания курьера без указания логина, курьер не создается и появляется сообщение об ошибке")
    public void createWithoutLoginTest()
    {
        courier = Courier.getWithoutLogin();
        String message = courierClient.createCourier(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Check error when create courier without password")
    @Description("Проверка, что при попытке создания курьера без указания пароля, курьер не создается и появляется сообщение об ошибке")
    public void createWithoutPasswordTest()
    {
        courier = Courier.getWithoutPassword();
        String message = courierClient.createCourier(courier).extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Check error when create courier copy")
    @Description("Проверка, что при создании дубля курьера, курьер не создается, статус 409 и нужное сообщение об ошибке")
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
