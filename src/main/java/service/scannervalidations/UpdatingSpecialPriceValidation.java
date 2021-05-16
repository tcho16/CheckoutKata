package service.scannervalidations;

import java.util.function.Predicate;

public class UpdatingSpecialPriceValidation implements Predicate<String[]> {

    private int firstElement = 0;

    @Override
    public boolean test(String[] scannerInput) {
        //Assert only capital letters in input
        if(!doesContain3Entries(scannerInput)) return false;
        if(!doesInputHaveCharacterInTheGivenElement(scannerInput[firstElement])) return false;

        for(String input : scannerInput){
            if(inputIsACharcterButHasMoreThanOneCharacter(input)) return false;
        }
        return true;
    }

    private boolean doesInputHaveCharacterInTheGivenElement(String s) {
        try{
            Integer.parseInt(s);
            return false;
        }catch(NumberFormatException e)
        {
            return true;
        }
    }

    private boolean inputIsACharcterButHasMoreThanOneCharacter(String input)
    {
        try{
            Integer.parseInt(input);
            return false;
        }catch(NumberFormatException e)
        {
            return input.length() > 1;
        }
    }

    private boolean doesContain3Entries(String[] scannerInput) {
        if(scannerInput.length != 3) return false;
        return true;
    }
}
