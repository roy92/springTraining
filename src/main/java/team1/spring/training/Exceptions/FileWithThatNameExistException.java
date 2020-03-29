package team1.spring.training.Exceptions;

public class FileWithThatNameExistException extends Exception{
    public FileWithThatNameExistException() {
        super("A file with that name has already exist!");
    }
}
