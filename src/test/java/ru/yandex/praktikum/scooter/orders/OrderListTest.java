package ru.yandex.praktikum.scooter.orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.order.OrderClient;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class OrderListTest
{
    private OrderClient orderClient;

    @Before
    public void setUp()
    {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check orders create")
    @Description("Проверка, что создаётся список заказов.")
    public void orderListIsNotEmptyTest()
    {
        ValidatableResponse response = orderClient.getDefaultOrder();
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode);
        List<String> bodyAnswer = response.extract().path("orders");
        assertFalse(bodyAnswer.isEmpty());
    }

    @Test
    @DisplayName("Check orders not null")
    @Description("Проверка, что в тело ответа возвращается список заказов.")
    public void orderListIsNotNull()
    {
        ValidatableResponse response = orderClient.getDefaultOrder();
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode);
        List<String> bodyAnswer = response.extract().path("orders");
        assertNotEquals(null, bodyAnswer);
    }
}
