package banka;

public class NegativeAmountException extends RuntimeException {

    public NegativeAmountException(String message) {
        super(message);
    }
}
