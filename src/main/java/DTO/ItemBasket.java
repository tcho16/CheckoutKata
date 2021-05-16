package DTO;

import java.util.*;

//This class is used to hold the items. It essentially holds the state of the application.
public class ItemBasket {
    private HashMap<Character, Item> items;

    public ItemBasket() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item) {
        this.items.put(item.getItemSKU(), item);
    }

    public Optional<Item> getItem(char itemSKU) {
        return Optional.ofNullable(items.get(Character.toUpperCase(itemSKU)));
    }

    public List<Item> getItems() {
        List<Item> listOfItems = new ArrayList<>();
        Iterator<Item> i = items.values().iterator();

        while (i.hasNext()) {
            listOfItems.add(i.next());
        }
        return listOfItems;
    }
}