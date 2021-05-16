package service.costlogic;

import DTO.Item;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ItemWithNoSpecialOfferCalculatorTest {

    @Test
    public void shouldCorrectlyCalculateTheAmount()
    {
        Item item = new Item('A', 10, Optional.empty());
        ItemWithNoSpecialOfferCalculator itemWithNoSpecialOfferCalculator = new ItemWithNoSpecialOfferCalculator();
        int calculatedCost = itemWithNoSpecialOfferCalculator.calculate(item, 10);

        assertEquals(100, calculatedCost);
    }
}
