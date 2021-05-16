package DTO;

public class SpecialOffer {
    private int offerQuantity;
    private int offerAmount;

    public SpecialOffer(int offerQuantity, int offerAmount) {
        this.offerAmount = offerAmount;
        this.offerQuantity = offerQuantity;
    }

    public int getOfferAmount() {
        return offerAmount;
    }

    public int getOfferQuantity() {
        return offerQuantity;
    }
}
