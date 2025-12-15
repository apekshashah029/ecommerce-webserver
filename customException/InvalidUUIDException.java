package customException;

import java.util.UUID;

public class InvalidUUIDException extends IllegalArgumentException{
    public InvalidUUIDException(String message){
        super(message);
    }

    public static UUID isValid(String input){
        try{
            return UUID.fromString(input);
        }catch (IllegalArgumentException e){
            throw new InvalidUUIDException("UUID is not valid");
        }
    }
}
