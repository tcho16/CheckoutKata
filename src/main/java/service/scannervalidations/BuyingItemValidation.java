package service.scannervalidations;

import java.util.function.Predicate;

public class BuyingItemValidation implements Predicate<String[]> {

    @Override
    public boolean test(String[] scannerInput) {
        //Assert only capital letters in input
        for(String input : scannerInput){
            if(input.length()<=0)return false;
            if(!doesInputNotContainANumber(input)) return false;
            if(inputIsNotASingleCharacter(input)) return false;
        }
        return true;
    }

    private boolean inputIsNotASingleCharacter(String input) {
        if(input.length() > 1){
            System.out.println("Ensure your entry/entries contain a single character and not like 'AA'");
            return true;
        }
        return false;
    }

    private boolean doesInputNotContainANumber(String input)
    {
        try{
            Integer.parseInt(input);
            System.out.println("No numbers allowed");
            return false;
        }catch(NumberFormatException e)
        {
            return true;
        }
    }
}
