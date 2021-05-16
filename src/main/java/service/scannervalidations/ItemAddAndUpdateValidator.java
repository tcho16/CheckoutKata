package service.scannervalidations;

import java.util.function.Predicate;

public class ItemAddAndUpdateValidator implements Predicate<String[]> {

    private int firstElement = 0;
    private int secondElement = 1;

    @Override
    public boolean test(String[] scannerInput) {
        //Assert only capital letters in input
        if(!doesContain2Entries(scannerInput)) return false;
        if(!doesInputHaveCharacterInElement(scannerInput[firstElement])) return false;
        if(inputDoesNotFollowFormat(scannerInput)) return false;

        for(String input : scannerInput){
            if(inputIsACharcterButHasMoreThanOneCharacter(input)) return false;
            if(inputHasNegativeNumber(input)) return false;
        }
        return true;
    }

    private boolean inputHasNegativeNumber(String input) {
        try{
            return Integer.parseInt(input) < 0;
        }catch(NumberFormatException e)
        {
            return false;
        }
    }

    private boolean inputDoesNotFollowFormat(String[] scannerInput) {
        if(!doesInputHaveCharacterInElement(scannerInput[firstElement]) && doesInputHaveCharacterInElement(scannerInput[secondElement]))
        {
            return true;
        }

        return false;
    }

    private boolean doesInputHaveCharacterInElement(String s) {
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

    private boolean doesContain2Entries(String[] scannerInput) {
        if(scannerInput.length != 2) return false;

        return true;
    }
}
