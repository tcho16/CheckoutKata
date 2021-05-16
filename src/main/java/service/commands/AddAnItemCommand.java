package service.commands;

import DTO.Command;
import DTO.Item;
import DTO.ItemBasket;

import java.util.Optional;

public class AddAnItemCommand implements Command {

    private final ItemBasket itemBasket;

    public AddAnItemCommand(ItemBasket itemBasket) {
        this.itemBasket = itemBasket;
    }

    @Override
    public void execute(String[] userInput) {
        char sku = userInput[0].charAt(0);
        int unitPrice = Integer.valueOf(userInput[1]);
        Item item = new Item(sku, unitPrice, Optional.empty());

        itemBasket.addItem(item);
    }
}
