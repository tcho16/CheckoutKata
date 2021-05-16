package service.costlogic;

import DTO.CalculateCost;
import DTO.Item;

public class ItemWithNoSpecialOfferCalculator implements CalculateCost {
    @Override
    public int calculate(Item item, int buyingQuantity) {
        return buyingQuantity * item.getUnitPrice();
    }
}
