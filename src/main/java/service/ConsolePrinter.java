package service;

import DTO.Item;

import java.util.Collection;

//Class purely for outputting things onto the console
public class ConsolePrinter {

    public void printItemsToConsole(Collection<Item> items)
    {
        for(Item i : items)
        {
            System.out.println(i.toString());
        }
    }

    public void printMenu(){
        System.out.println("Press the following keys to perform actions");
        System.out.println("1. Perform checkout");
        System.out.println("2. Update price");
        System.out.println("3. Add an item");
        System.out.println("4. Update special offer for an item");
        System.out.println("5. Print out item price list");
        System.out.println("0. Exit");
    }

    public void printUserInstructionsForBuying() {
        System.out.println("Type the items you want to buy in CSV format, e.g. A,B,C etc.");
    }

    public void printTotal(int runningTotal) {
        System.out.println("The total of the items you bought comes to " + runningTotal);
    }

    public void printUserInstructionsForUpdatingPrice() {
        System.out.println("Enter the SKU for the item you want to update the price for and the price in csv format with no spaces, e.g. A,50 to change item A to price 50.");
    }

    public void printInstructionsForAddingAnItem() {
        System.out.println("Enter the SKU (has to be a character) and unit price for the new item in CSV format with no spaces, e.g. Z,250");
    }

    public void printInstructionsForUpdatingSpecialOffer() {
        System.out.println("Enter the SKU of the item you wish to update the special offer by followed by the offer quantity and offer ammount with no spaces. E.g, item B to have a special offer of 5 for 500: B,5,500");
    }

    public void printOutError() {
        System.out.println("Invalid input received. Ensure you follow the correct format");
    }

    public void printMissingItem() {
        System.out.println("Unable to find the item you are qurerying for");
    }

    public void printMissingItemMessageCheckout(char itemSku) {
        System.out.println("Unable to find item with sku of " + itemSku + ". But will continue calculating the total of the other items you want to checkout.");
    }

    public void printGreetingMessage() {
        System.out.println("NOTE: SKUs are NOT case sensitive. \nWelcome to the supermarket checkout. Today's prices are:");
    }

    public void printMenuTipToConsle() {
        System.out.println("Ensure you select a menu item.");
    }

    public void printMenuErrorToConsole() {
        System.out.println("Ensure you type numbers that conform to the item list.");
    }
}