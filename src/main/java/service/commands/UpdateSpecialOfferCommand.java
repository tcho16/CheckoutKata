package service.commands;

import DTO.Command;
import DTO.ItemBasket;
import DTO.SpecialOffer;
import service.ConsolePrinter;

import java.util.Optional;

public class UpdateSpecialOfferCommand implements Command
{
    private final ItemBasket itemBasket;
    private final ConsolePrinter consolePrinter;

    public UpdateSpecialOfferCommand(ItemBasket itemBasket, ConsolePrinter consolePrinter)
    {
        this.itemBasket = itemBasket;
        this.consolePrinter = consolePrinter;
    }

    @Override
    public void execute(String[] userInput) {
        char itemSku = userInput[0].charAt(0);
        int specialOfferCost = Integer.valueOf(userInput[2]);
        int specialOfferQuantity = Integer.valueOf(userInput[1]);

        if(itemBasket.getItem(itemSku).isPresent())
        {
            itemBasket.getItem(itemSku).get().setSpecialOffer(Optional.of(new SpecialOffer(specialOfferQuantity, specialOfferCost)));
        }else {
            consolePrinter.printMissingItem();
        }
    }
}
