package exception;

public class TypeMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String expectedType;
	private String actualType;
	
	public String getMessage() {
		return String.format("Mismatch in property type. Expected: %s, Actual: %s", expectedType, actualType) + super.getMessage();
	}
	
	public TypeMismatchException(String expected, String actual) {
		this.expectedType = expected;
		this.actualType = actual;
	}
}
