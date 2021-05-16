package service.ScannerValidations;

import org.junit.Test;
import service.scannervalidations.ItemAddAndUpdateValidator;

import static org.junit.Assert.assertEquals;

public class ItemAddAndUpdateValidatorTest {

    ItemAddAndUpdateValidator itemAddAndUpdateValidator = new ItemAddAndUpdateValidator();

    @Test
    public void shouldNotPassWhenLessThan2EntriesIsPassedIn()
    {        String[] input = {"A"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldNotPassWhenIncorrectPositioningIsPassedIn()
    {        String[] input = {"34","A"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldNotPassWhenNegativeNumberIsPassedIn()
    {        String[] input = {"A","-34"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldFailWhenMoreThan2EntriesIsPassedIn()
    {        String[] input = {"A","34","z"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, false);

    }

    @Test
    public void shouldPassWhenExactly2EntriesIsPassedIn()
    {        String[] input = {"A","34"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, true);

    }

    @Test
    public void firstArgumentMustBeCharacter()
    {        String[] input = {"23","A"};
        boolean resultOfValidation = itemAddAndUpdateValidator.test(input);

        assertEquals(resultOfValidation, false);

    }
}
