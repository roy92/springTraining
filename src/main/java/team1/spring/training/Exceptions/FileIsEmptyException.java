package team1.spring.training.Exceptions;

public class FileIsEmptyException extends Exception {
    public FileIsEmptyException() {
        super("The file is empty!");
    }
}
