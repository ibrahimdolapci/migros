package tests.purchase;

import org.junit.Test;
import tests.AbstractTest;

public class BasketTest extends AbstractTest
{
    @Test
    public void testBasketItem()
    {
        login(context.getInternalProps().getUsername(), context.getInternalProps().getPassword());
    }
}
