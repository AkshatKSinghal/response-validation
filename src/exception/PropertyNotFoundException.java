package exception;

public class PropertyNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String            missingProperty;

    public PropertyNotFoundException(String property) {
        this.missingProperty = property;
    }

    public String getMessage() {
        return "Property not found " + missingProperty + super.getMessage();
    }
}
