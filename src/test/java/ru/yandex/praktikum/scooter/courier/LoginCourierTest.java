package ru.yandex.praktikum.scooter.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginCourierTest
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
    @DisplayName("Check courier login")
    @Description("Проверка, логина курьера со всеми валидными параметрами")
    public void successLoginTest()
    {
        boolean isOk = courierClient.createCourier(courier).extract().path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .extract().path("id");
        assertTrue(isOk);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check courier login without login")
    @Description("Проверка, что при логине курьера без логина появляется соответствующее сообщение об ошибке")
    public void loginWithoutLoginTest()
    {
        CourierCredsWithoutLogin creds = CourierCredsWithoutLogin.from(courier);
        String message = courierClient.loginWithoutLogin(creds).extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Check courier login without password")
    @Description("Проверка, что при логине курьера без пароля появляется соответствующее сообщение об ошибке")
    public void loginWithoutPasswordTest()
    {
        CourierCredsWithoutPassword creds = CourierCredsWithoutPassword.from(courier);
        String message = courierClient.loginWithoutPass(creds).extract().path("message");
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Check courier login with wrong login")
    @Description("Проверка, что при логине курьера с неверным логином появляется соответствующее сообщение об ошибке")
    public void loginWrongCredsTest()
    {
        CourierWrongCreds creds = CourierWrongCreds.from(courier);
        String message = courierClient.loginWrongCreds(creds).extract().path("message");
        assertEquals("Учетная запись не найдена", message);
    }
}
