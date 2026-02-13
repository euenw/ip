package aurafarmer;

/**
 * Represents an exception specific to the AuraFarmer application.
 */
public class AuraFarmerException extends Exception {

    /**
     * Constructs a new AuraFarmerException with the given error message.
     *
     * @param message Error message describing the exception.
     */
    public AuraFarmerException(String message) {
        super(message);
    }
}
