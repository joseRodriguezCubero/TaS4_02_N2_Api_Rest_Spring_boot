package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.exceptions;

public class FruitAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FruitAlreadyExistException(String msg) {
        super(msg);
    }
}