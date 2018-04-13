public class InvalidEngNumberException extends RuntimeException {

    public InvalidEngNumberException(String msg) {
        super("invalid line for input: " + msg);
    }
}
