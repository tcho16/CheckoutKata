package service.commands;

import DTO.Item;
import DTO.ItemBasket;
import DTO.SpecialOffer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import service.ConsolePrinter;
import service.commands.CheckoutCommand;
import service.costlogic.ItemWithNoSpecialOfferCalculator;
import service.costlogic.ItemWithSpecialOfferCalculator;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddAnItemCommandTest {

    private ItemBasket itemBasket;
    private AddAnItemCommand addAnItemCommand;

    @Before
    public void setUp()
    {
        itemBasket = new ItemBasket();
        Item A = new Item('A', 50, createSpecialOffer(3, 130));
        Item B = new Item('B', 30, createSpecialOffer(2, 45));
        Item C = new Item('C', 20, Optional.empty());
        Item D = new Item('D', 15, Optional.empty());

        itemBasket.addItem(A);
        itemBasket.addItem(B);
        itemBasket.addItem(C);
        itemBasket.addItem(D);
    }

    private Optional<SpecialOffer> createSpecialOffer(int offerQuantity, int offerAmount) {
        return Optional.of(new SpecialOffer(offerQuantity, offerAmount));
    }

    @Test
    public void shouldCorrectlyAddAnItem()
    {
        String[] itemsToBuy = {"Z","230"};
        this.addAnItemCommand = new AddAnItemCommand(itemBasket);

        addAnItemCommand.execute(itemsToBuy);

        assertNotNull(itemBasket.getItem('Z').get());
    }

    @Test
    public void shouldCorrectlyUpdateExistingItemIfSKUIsSameButDifferentCases()
    {
        char lowerCaseSKU = 'a';
        char upperCaseSKU = 'A';
        String[] itemsToBuy = {String.valueOf(lowerCaseSKU),"230"};
        this.addAnItemCommand = new AddAnItemCommand(itemBasket);
        addAnItemCommand.execute(itemsToBuy);

        assertNotNull(itemBasket.getItem(upperCaseSKU).get());
        assertEquals(230, itemBasket.getItem(upperCaseSKU).get().getUnitPrice());
    }

    @Test
    public void shouldCorrectlyAddAnItemAndHaveCorrectPrice()
    {
        String[] itemsToBuy = {"Z","230"};
        this.addAnItemCommand = new AddAnItemCommand(itemBasket);
        addAnItemCommand.execute(itemsToBuy);

        assertEquals(230, itemBasket.getItem('Z').get().getUnitPrice());
    }
}