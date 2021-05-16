package DTO;

import java.util.Optional;

public class Item {

    private char itemSKU;
    private int unitPrice;
    private Optional<SpecialOffer> specialOffer;

    public Item(char itemSKU, int unitPrice, Optional<SpecialOffer> specialOffer) {
        this.itemSKU = Character.toUpperCase(itemSKU);
        this.unitPrice = unitPrice;
        this.specialOffer = specialOffer;
    }

    public char getItemSKU() {
        return itemSKU;
    }

    public void setItemSKU(char itemSKU) {
        this.itemSKU = Character.toUpperCase(itemSKU);
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Optional<SpecialOffer> getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(Optional<SpecialOffer> specialOffer) {
        this.specialOffer = specialOffer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item " + this.itemSKU + " is for " + this.unitPrice + ".");
        specialOffer.ifPresent(specialOffer -> {
            stringBuilder.append(" Also has a special offer of buy " + specialOffer.getOfferQuantity() + " for " + specialOffer.getOfferAmount());
        });
        return stringBuilder.toString();

    }
}
