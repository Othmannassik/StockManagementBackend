package ma.cih.stockmanagementbackend.exceptions;

public class CommandeNotFoundException extends Exception {
    public CommandeNotFoundException(String message) {
        super(message);
    }
}
