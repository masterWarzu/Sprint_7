package orders;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class OrdersListTest
{
    Orders orders;
    OrdersClient ordersClient;

    @Before
    public void setup()
    {
        orders = Orders.getRandomOrder();
        ordersClient = new OrdersClient();
    }

    @Test
    public void checkOrderListIsNotEmptyTest()
    {
        ArrayList<String> ordersList = ordersClient.getList(orders).extract().path("orders");
        assertNotEquals(null, ordersList);
    }
}
