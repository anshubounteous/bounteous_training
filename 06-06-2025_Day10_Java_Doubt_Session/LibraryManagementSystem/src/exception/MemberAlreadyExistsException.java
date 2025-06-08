package exception;

public class MemberAlreadyExistsException extends Exception{
    public MemberAlreadyExistsException(String message){
        super(message);
    }
}
