

import DTO.ItemBasket;
import service.CheckoutTill;
import service.ConsolePrinter;
import service.commands.AddAnItemCommand;
import service.commands.CheckoutCommand;
import service.commands.UpdatePriceOfAnItemCommand;
import service.commands.UpdateSpecialOfferCommand;
import service.costlogic.ItemWithNoSpecialOfferCalculator;
import service.costlogic.ItemWithSpecialOfferCalculator;
import service.scannervalidations.BuyingItemValidation;
import service.scannervalidations.ItemAddAndUpdateValidator;
import service.scannervalidations.UpdatingSpecialPriceValidation;

public class Main {

    public static void main(String[] args) {

        ConsolePrinter consolePrinter = new ConsolePrinter();
        ItemBasket itemBasket = new ItemBasket();
        ItemWithNoSpecialOfferCalculator itemWithNoSpecialOfferCalculator = new ItemWithNoSpecialOfferCalculator();
        ItemWithSpecialOfferCalculator itemWithSpecialOfferCalculator = new ItemWithSpecialOfferCalculator();

        CheckoutTill checkoutTill = new CheckoutTill(
                itemBasket,
                consolePrinter,
                new BuyingItemValidation(),
                new ItemAddAndUpdateValidator(),
                new UpdatingSpecialPriceValidation(),
                new CheckoutCommand(itemBasket, consolePrinter, itemWithSpecialOfferCalculator, itemWithNoSpecialOfferCalculator),
                new AddAnItemCommand(itemBasket),
                new UpdatePriceOfAnItemCommand(itemBasket, consolePrinter),
                new UpdateSpecialOfferCommand(itemBasket, consolePrinter));
    }
}