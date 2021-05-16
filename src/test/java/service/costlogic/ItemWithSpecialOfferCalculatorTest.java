package service.costlogic;

import DTO.Item;
import DTO.SpecialOffer;
import org.junit.Test;
import service.costlogic.ItemWithNoSpecialOfferCalculator;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ItemWithSpecialOfferCalculatorTest {

    @Test
    public void shouldCorrectlyCalculateTheAmount()
    {
        Item item = new Item('A', 10, Optional.of(buildSpecialOffer(10, 5)));
        ItemWithSpecialOfferCalculator itemWithSpecialOfferCalculator = new ItemWithSpecialOfferCalculator();

        int calculatedCost = itemWithSpecialOfferCalculator.calculate(item, 10);

        assertEquals(5, calculatedCost);
    }

    @Test
    public void shouldCorrectlyCalculateTheAmountEvenWhenItemsDontAddUpToTheOfferQuantity()
    {
        Item item = new Item('A', 10, Optional.of(buildSpecialOffer(10, 5)));
        ItemWithSpecialOfferCalculator itemWithSpecialOfferCalculator = new ItemWithSpecialOfferCalculator();

        int calculatedCost = itemWithSpecialOfferCalculator.calculate(item, 15);

        assertEquals(55, calculatedCost);
    }

    private SpecialOffer buildSpecialOffer(int offerQuantity, int offerAmount){
        return new SpecialOffer(offerQuantity,offerAmount);
    }
}
