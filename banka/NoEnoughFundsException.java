package banka;

public class NoEnoughFundsException extends RuntimeException {

    public NoEnoughFundsException(String message) {
        super(message);
    }

}
