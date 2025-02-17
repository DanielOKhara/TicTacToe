package exception;

public class WrongUsersInputException extends RuntimeException{
    public WrongUsersInputException(String message) {
        super(message);
    }
}
