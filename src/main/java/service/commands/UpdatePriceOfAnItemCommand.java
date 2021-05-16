package service.commands;

import DTO.Command;
import DTO.Item;
import service.ConsolePrinter;
import DTO.ItemBasket;

import java.util.Optional;

public class UpdatePriceOfAnItemCommand implements Command {

    private final ItemBasket itemBasket;
    private final ConsolePrinter consolePrinter;

    public UpdatePriceOfAnItemCommand(ItemBasket itemBasket, ConsolePrinter consolePrinter)
    {
        this.itemBasket = itemBasket;
        this.consolePrinter = consolePrinter;
    }

    @Override
    public void execute(String[] userInput) {
        Optional<Item> item = itemBasket.getItem(userInput[0].charAt(0));
        if(item.isPresent())
        {
            item.get().setUnitPrice(Integer.valueOf(userInput[1]));
        } else {
            consolePrinter.printMissingItem();
        }
    }
}
