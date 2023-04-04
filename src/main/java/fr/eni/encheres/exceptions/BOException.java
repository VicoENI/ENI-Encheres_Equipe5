package fr.eni.encheres.exceptions;

/**
 * Class in charge of managing the exceptions of the DAL layer
 * @author VicoENI
 * @version 1.0
 */
public class BOException extends Exception {

    /**
     * Constructor in charge of initializing the message attribute with the message passed in parameter
     * @param message String
     */
    public BOException(String message) {
        super(message);
    }

    /**
     * Constructor in charge of initializing the message attribute with the message passed in parameter
     * and the cause attribute with the cause passed in parameter
     * @param message String
     * @param cause Throwable
     */
    public BOException(String message, Throwable cause) {
        super(message, cause);
    }
}