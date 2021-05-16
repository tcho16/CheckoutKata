package service.commands;

import DTO.Item;
import DTO.ItemBasket;
import DTO.SpecialOffer;
import org.junit.Before;
import org.junit.Test;
import service.ConsolePrinter;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class UpdateSpecialOfferCommandTest {

    private ConsolePrinter consolePrinter =  mock(ConsolePrinter.class);
    private ItemBasket itemBasket = new ItemBasket();
    private UpdateSpecialOfferCommand updateSpecialOfferCommand;

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
    public void shouldCorrectlyAddASpecialOfferToAnItemWithNoExistingSpecialOffer()
    {
        int offerAmount = 450;
        int offerQuantity = 30;
        String[] itemsToUpdate = {"A", String.valueOf(offerQuantity), String.valueOf(offerAmount)};
        this.updateSpecialOfferCommand = new UpdateSpecialOfferCommand(itemBasket, consolePrinter);
        updateSpecialOfferCommand.execute(itemsToUpdate);

        assertNotNull(itemBasket.getItem('A').get());
        assertNotNull(itemBasket.getItem('A').get().getSpecialOffer());
        assertEquals(offerQuantity, itemBasket.getItem('A').get().getSpecialOffer().get().getOfferQuantity());
        assertEquals(offerAmount, itemBasket.getItem('A').get().getSpecialOffer().get().getOfferAmount());
    }

    @Test
    public void shouldCorrectlyAddASpecialOfferToAnItemWithAnExistingSpecialOffer()
    {
        SpecialOffer specialOffer = buildSpecialOffer(5, 10);
        itemBasket.addItem(new Item('T', 45, Optional.of(specialOffer)));

        int offerQuantity = 99;
        int offerAmount = 999;

        String[] itemsToUpdate = {"T", String.valueOf(offerQuantity), String.valueOf(offerAmount)};
        this.updateSpecialOfferCommand = new UpdateSpecialOfferCommand(itemBasket, consolePrinter);
        updateSpecialOfferCommand.execute(itemsToUpdate);

        assertNotNull(itemBasket.getItem('T').get());
        assertNotNull(itemBasket.getItem('T').get().getSpecialOffer());
        assertEquals(offerQuantity, itemBasket.getItem('T').get().getSpecialOffer().get().getOfferQuantity());
        assertEquals(offerAmount, itemBasket.getItem('T').get().getSpecialOffer().get().getOfferAmount());
    }

    private SpecialOffer buildSpecialOffer(int offer, int quantity){
        return new SpecialOffer(quantity, offer);
    }
}