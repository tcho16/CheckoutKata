package service.ScannerValidations;

import org.junit.Test;
import service.scannervalidations.BuyingItemValidation;

import static org.junit.Assert.assertEquals;

public class BuyingItemValidationTest {
    BuyingItemValidation buyingItemValidation = new BuyingItemValidation();

    @Test
    public void shouldFailWhenUserInputsIncorrectData()
    {
        String[] input = {"A","D","E","45"};
        boolean resultOfValidation = buyingItemValidation.test(input);

        assertEquals(resultOfValidation, false);
    }

    @Test
    public void shouldNotPassWhenEmptyElementIsPassedIn(){
        String[] input = {"A", "B","","W"};
        boolean resultOfValidation = buyingItemValidation.test(input);

        assertEquals(resultOfValidation, false);
    }

    @Test
    public void shouldPassWhenUserInputASingleCorrectEntry()
    {
        String[] input = {"A"};
        boolean resultOfValidation = buyingItemValidation.test(input);

        assertEquals(resultOfValidation, true);
    }

    @Test
    public void shouldPassWhenUserInputsCorrectData()
    {
        String[] input = {"A","D","E"};
        boolean resultOfValidation = buyingItemValidation.test(input);

        assertEquals(resultOfValidation, true);
    }
}
