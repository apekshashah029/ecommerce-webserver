package customException;

import java.util.UUID;

public class InvalidFormatException extends IllegalArgumentException{
    public InvalidFormatException(String message){
        super(message);
    }

    public InvalidFormatException(String message, String correctFormat){
        super(message +"\n" + "Correct format is: "+correctFormat);
    }

    public static UUID isValid(String input){
        try{
            return UUID.fromString(input);
        }catch (IllegalArgumentException e){
            throw new InvalidFormatException("UUID is not valid ", "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
        }
    }
}
