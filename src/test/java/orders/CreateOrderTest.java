package orders;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateOrderTest
{
    Orders orders;
    OrdersClient ordersClient;
    private int track;

    @Before
    public void setup()
    {
        orders = Orders.getRandomOrder();
        ordersClient = new OrdersClient();
    }

    @Test
    public void createNonColourOrderTest()
    {
        track = ordersClient.create(orders).extract().path("track");
        assertNotEquals(0, track);
    }

    @Test
    public void createBlackOrderTest()
    {
        orders = Orders.getBlackOrder();
        track = ordersClient.create(orders).extract().path("track");
        assertNotEquals(0, track);
    }

    @Test
    public void createGreyOrderTest()
    {
        orders = Orders.getGreyOrder();
        track = ordersClient.create(orders).extract().path("track");
        assertNotEquals(0, track);
    }

    @Test
    public void createBlackGreyOrderTest()
    {
        orders = Orders.getBlackGreyOrder();
        track = ordersClient.create(orders).extract().path("track");
        assertNotEquals(0, track);
    }
}
