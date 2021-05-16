package service.ScannerValidations;

import org.junit.Test;
import service.scannervalidations.ItemAddAndUpdateValidator;
import service.scannervalidations.UpdatingSpecialPriceValidation;

import static org.junit.Assert.assertEquals;

public class UpdatingSpecialPriceValidatorTest {

    UpdatingSpecialPriceValidation updatingSpecialPriceValidation = new UpdatingSpecialPriceValidation();

    @Test
    public void shouldNotPassWhenLessThan3EntriesIsPassedIn()
    {        String[] input = {"A"};
        boolean resultOfValidation = updatingSpecialPriceValidation.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldFailWhenMoreThan3EntriesIsPassedIn()
    {        String[] input = {"A","34","z", "er"};
        boolean resultOfValidation = updatingSpecialPriceValidation.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldPassWhenExactly3EntriesIsPassedIn()
    {        String[] input = {"A","34", "4"};
        boolean resultOfValidation = updatingSpecialPriceValidation.test(input);

        assertEquals(resultOfValidation, true);

    }

    @Test
    public void shouldPassWhenCorrectInputIsPassedIn()
    {
        String[] input = {"A","34","30"};
        boolean resultOfValidation = updatingSpecialPriceValidation.test(input);

        assertEquals(resultOfValidation, true);
    }

    @Test
    public void firstArgumentMustBeCharacter()
    {        String[] input = {"23","A"};
        boolean resultOfValidation = updatingSpecialPriceValidation.test(input);

        assertEquals(resultOfValidation, false);
    }
}
