package ru.yandex.praktikum.scooter.orders;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.order.Order;
import ru.yandex.praktikum.scooter.order.OrderClient;
import ru.yandex.praktikum.scooter.order.OrderFactory;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest
{
    private OrderClient orderClient;
    private final String[] color;

    public CreateOrderTest(String color)
    {
        this.color = new String[]{color};
    }

    @Before
    public void setUp()
    {
        orderClient = new OrderClient();
    }

    @Parameterized.Parameters
    public static Object[][] setColor()
    {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {""},
                {"BLACK , GREY"}
        };
    }

    @Test
    public void orderCreatedTest()
    {
        Order order = OrderFactory.getWithoutColor(color);
        ValidatableResponse response = orderClient.create(order);

        int statusCode = response.extract().statusCode();
        assertEquals(SC_CREATED, statusCode);

        int track = response.extract().path("track");
        assertNotEquals(0, track);
    }
}
