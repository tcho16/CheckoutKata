package service.commands;

import DTO.CalculateCost;
import DTO.Command;
import DTO.Item;
import service.ConsolePrinter;
import DTO.ItemBasket;

import java.util.*;

//Performs the action of totalling the items you bought
public class CheckoutCommand implements Command {

    private final ItemBasket itemBasket;
    private final ConsolePrinter consolePrinter;
    private final CalculateCost itemWithOfferCalculator;
    private final CalculateCost itemWithoutOfferCalculator;
    private HashMap<Character, Integer> mapOfSkuAndQuantityBought;
    private int runningTotal = 0;

    public CheckoutCommand(ItemBasket itemBasket,
                           ConsolePrinter consolePrinter,
                           CalculateCost itemWithOfferCalculator,
                           CalculateCost itemWithoutOfferCalculator) {
        this.itemBasket = itemBasket;
        this.consolePrinter = consolePrinter;
        this.itemWithOfferCalculator = itemWithOfferCalculator;
        this.itemWithoutOfferCalculator = itemWithoutOfferCalculator;
    }

    private void populateMapOfItemsWantingToBuy(String[] itemsThatUserWantsToBuy) {
        mapOfSkuAndQuantityBought = new HashMap<>();
        for (String itemSKU : itemsThatUserWantsToBuy) {
            char itemSKUChar = Character.toUpperCase(itemSKU.charAt(0));
            if (mapOfSkuAndQuantityBought.containsKey(itemSKUChar)) {
                Integer increaseQuantityBought = mapOfSkuAndQuantityBought.get(itemSKUChar) + 1;
                mapOfSkuAndQuantityBought.put(itemSKUChar, increaseQuantityBought);
            } else {
                mapOfSkuAndQuantityBought.put(Character.toUpperCase(itemSKU.charAt(0)), 1);
            }
        }
    }

    //Buying logic:
    //Keep a tally of occurances of items in a hashmap
    //increase the value every of item everytime it's being passed in
    //do a mod on the amounts bought to get the number of items that doesn't fall into the offer
    //do a division on the amounts bought to get number of items that fell into the offer
    @Override
    public void execute(String[] input) {
        runningTotal = 0;
        populateMapOfItemsWantingToBuy(input);


        mapOfSkuAndQuantityBought.entrySet()
                .stream()
                .forEach(mapEntry -> {
                    int buyingQuantity = mapEntry.getValue();
                    char itemSku = mapEntry.getKey();
                    Optional<Item> retreivedItem = itemBasket.getItem(itemSku);

                    if (retreivedItem.isPresent()) {
                        Item item = retreivedItem.get();
                        if (item.getSpecialOffer().isPresent()) {
                            runningTotal = runningTotal + itemWithOfferCalculator.calculate(item, buyingQuantity);
                        } else {
                            runningTotal = runningTotal + itemWithoutOfferCalculator.calculate(item, buyingQuantity);
                        }
                    } else {
                        consolePrinter.printMissingItemMessageCheckout(itemSku);
                    }
                });
        consolePrinter.printTotal(runningTotal);
    }
}