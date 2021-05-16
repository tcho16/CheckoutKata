package service.commands;

import DTO.Item;
import DTO.ItemBasket;
import org.junit.Before;
import org.junit.Test;
import service.ConsolePrinter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UpdatePriceOfAnItemCommandTest {

    private ConsolePrinter consolePrinter =  mock(ConsolePrinter.class);
    private ItemBasket itemBasket = new ItemBasket();
    private UpdatePriceOfAnItemCommand updatePriceOfAnItemCommand;

    @Before
    public void setUp() {

        Item A = new Item('A', 50, null);
        Item B = new Item('B', 30, null);
        Item C = new Item('C', 20, null);
        Item D = new Item('D', 15, null);

        itemBasket.addItem(A);
        itemBasket.addItem(B);
        itemBasket.addItem(C);
        itemBasket.addItem(D);
    }

    @Test
    public void shouldCorrectlyUpdateTheItemsInTheState()
    {
        String[] itemsToUpdate = {"A","30"};
        this.updatePriceOfAnItemCommand = new UpdatePriceOfAnItemCommand(itemBasket, consolePrinter);
        updatePriceOfAnItemCommand.execute(itemsToUpdate);

        assertEquals(30, itemBasket.getItem('A').get().getUnitPrice());
    }

    @Test
    public void shouldCorrectlyUpdateTheItemRegardlessOfCaseSensitiveSKU()
    {
        String[] itemsToUpdate = {"a","30"};
        this.updatePriceOfAnItemCommand = new UpdatePriceOfAnItemCommand(itemBasket, consolePrinter);
        updatePriceOfAnItemCommand.execute(itemsToUpdate);

        assertEquals(30, itemBasket.getItem('A').get().getUnitPrice());
    }
}