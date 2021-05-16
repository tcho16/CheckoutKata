package service.costlogic;

import DTO.CalculateCost;
import DTO.Item;

public class ItemWithSpecialOfferCalculator implements CalculateCost {

    @Override
    public int calculate(Item item, int buyingQuantity) {
        int priceThatFellIntoOffer = (buyingQuantity / item.getSpecialOffer().get().getOfferQuantity()) * item.getSpecialOffer().get().getOfferAmount();
        int priceThatNeedsToBeBoughtSeperately = (buyingQuantity % item.getSpecialOffer().get().getOfferQuantity()) * item.getUnitPrice();

        return priceThatFellIntoOffer + priceThatNeedsToBeBoughtSeperately;
    }
}
