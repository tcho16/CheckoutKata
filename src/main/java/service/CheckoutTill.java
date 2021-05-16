package service;

import DTO.Command;
import DTO.Item;
import DTO.ItemBasket;
import DTO.SpecialOffer;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

//The main class for performing checkout operations such as totalling the cost, updating price etc.
//It displays an interactive menu on the console in which it takes input from the user based on the number they type.
public class CheckoutTill {

    private final Predicate buyingItemValidator;
    private final Predicate itemAddUpdateValidator;
    private final Predicate updatingSpecialPriceValidation;
    private final Command checkoutCommand;
    private final Command addAnItemCommand;
    private final Command updatePriceCommand;
    private final Command updateSpecialOfferCommand;
    private boolean exitFlag = false;
    private ItemBasket itemBasket;
    private ConsolePrinter consolePrinter;

    public CheckoutTill(
            ItemBasket itemBasket,
            ConsolePrinter consolePrinter,
            Predicate buyingItemValidator,
            Predicate itemAddUpdateValidator,
            Predicate updatingSpecialPriceValidation,
            Command checkoutCommand,
            Command addAnItemCommand,
            Command updatePriceCommand,
            Command updateSpecialOfferCommand) {
        this.consolePrinter = consolePrinter;
        this.buyingItemValidator = buyingItemValidator;
        this.itemAddUpdateValidator = itemAddUpdateValidator;
        this.updatingSpecialPriceValidation = updatingSpecialPriceValidation;
        this.checkoutCommand = checkoutCommand;
        this.addAnItemCommand = addAnItemCommand;
        this.updatePriceCommand = updatePriceCommand;
        this.updateSpecialOfferCommand = updateSpecialOfferCommand;

        this.itemBasket = itemBasket;
        populateInitialItems(itemBasket);
        mainMenu();
    }

    private void mainMenu() {
        consolePrinter.printGreetingMessage();
        consolePrinter.printItemsToConsole(itemBasket.getItems());

        while (exitFlag != true) {
            consolePrinter.printMenu();
            Scanner scanner = new Scanner(System.in);
            try {
                int userChoice = scanner.nextInt();

                if (userChoice == 0) {
                    exitTheProgram();
                } else if (userChoice == 1) {
                    buyItems(scanner);
                } else if (userChoice == 2) {
                    updatePriceOfAnItem(scanner);
                } else if (userChoice == 3) {
                    addAnItem(scanner);
                } else if (userChoice == 4) {
                    updateSpecialOffer(scanner);
                } else if (userChoice == 5) {
                    consolePrinter.printItemsToConsole(itemBasket.getItems());
                } else {
                    consolePrinter.printMenuTipToConsle();
                }
            } catch (InputMismatchException e) {
                consolePrinter.printMenuErrorToConsole();
            }
        }
    }

    private void buyItems(Scanner scanner) {
        consolePrinter.printUserInstructionsForBuying();
        String[] userInput = scanner.next().split(",");
        if (buyingItemValidator.test(userInput)) {
            checkoutCommand.execute(userInput);
        } else {
            consolePrinter.printOutError();
        }
    }

    private void updatePriceOfAnItem(Scanner scanner) {
        consolePrinter.printUserInstructionsForUpdatingPrice();
        String[] itemToUpdate = scanner.next().split(",");
        if (itemAddUpdateValidator.test(itemToUpdate)) {
            updatePriceCommand.execute(itemToUpdate);
        } else {
            consolePrinter.printOutError();
        }
    }

    private void addAnItem(Scanner scanner) {
        consolePrinter.printInstructionsForAddingAnItem();
        String[] itemToUpdate = scanner.next().split(",");
        if (itemAddUpdateValidator.test(itemToUpdate)) {
            addAnItemCommand.execute(itemToUpdate);
        } else {
            consolePrinter.printOutError();
        }
    }

    private void updateSpecialOffer(Scanner scanner) {
        consolePrinter.printInstructionsForUpdatingSpecialOffer();
        String[] specialOfferToUpdate = scanner.next().split(",");
        if (updatingSpecialPriceValidation.test(specialOfferToUpdate)) {
            updateSpecialOfferCommand.execute(specialOfferToUpdate);
        } else {
            consolePrinter.printOutError();
        }
    }

    private void exitTheProgram() {
        exitFlag = true;
    }

    private void populateInitialItems(ItemBasket itemBasket) {
        Item A = new Item('A', 50, Optional.of(new SpecialOffer(3, 130)));
        Item B = new Item('B', 30, Optional.of(new SpecialOffer(2, 45)));
        Item C = new Item('C', 20, Optional.empty());
        Item D = new Item('D', 15, Optional.empty());

        itemBasket.addItem(A);
        itemBasket.addItem(B);
        itemBasket.addItem(C);
        itemBasket.addItem(D);
    }
}