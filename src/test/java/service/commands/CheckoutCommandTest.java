package service.commands;

import DTO.Item;
import DTO.ItemBasket;
import DTO.SpecialOffer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import service.ConsolePrinter;
import service.costlogic.ItemWithNoSpecialOfferCalculator;
import service.costlogic.ItemWithSpecialOfferCalculator;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheckoutCommandTest {

    private ConsolePrinter consolePrinter =  mock(ConsolePrinter.class);
    private ItemBasket itemBasket = new ItemBasket();
    private CheckoutCommand checkoutCommand;

    @Before
    public void setUp()
    {

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
    public void shouldTotal180WhenBuyingAnItem4TimesThatHasAnOfferOf3For130()
    {
        String[] itemsToBuy = {"A","A","A","A"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());

        checkoutCommand.execute(itemsToBuy);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(180), argument.getValue());
    }

    @Test
    public void shouldTotal130WhenBuyingAnItem3TimesThatHasAnOfferOf3For130()
    {
        String[] itemsToBuy = {"A","A","A"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());

        checkoutCommand.execute(itemsToBuy);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(130), argument.getValue());
    }

    @Test
    public void shouldTotal150WhenBuyingAnItem3TimesThatHasAnOfferOf3For130AndAnItemThatHas20()
    {
        String[] itemsToBuy = {"A","A","A","C"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());

        checkoutCommand.execute(itemsToBuy);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(150), argument.getValue());
    }

    @Test
    public void shouldTotal35IfIBuyAnItemPricedAt20AndAnItemPricedAt15()
    {
        String[] itemsToBuy = {"D","C"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());
        checkoutCommand.execute(itemsToBuy);

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(35), argument.getValue());
    }

    @Test
    public void shouldTotal30IfIBuy2ItemsPricedAt15()
    {
        String[] itemsToBuy = {"D","D"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());
        checkoutCommand.execute(itemsToBuy);

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(30), argument.getValue());
    }

    @Test
    public void shouldReturnCorrectValueIfLowerCaseSKUIsPassedIn()
    {
        String[] itemsToBuy = {"A","a","a","a","a","a"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());
        checkoutCommand.execute(itemsToBuy);

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(260), argument.getValue());
    }

    @Test
    public void shouldTotalTheCostDespiteBeingPassedSKUThatDoesNotExist()
    {
        String[] itemsToBuy = {"A","a","a","y"};
        this.checkoutCommand = new CheckoutCommand(itemBasket, consolePrinter, new ItemWithSpecialOfferCalculator(), new ItemWithNoSpecialOfferCalculator());
        checkoutCommand.execute(itemsToBuy);

        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(consolePrinter).printTotal(argument.capture());
        assertEquals(Integer.valueOf(130), argument.getValue());
    }
}
