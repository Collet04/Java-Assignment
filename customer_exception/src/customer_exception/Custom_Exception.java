// Step 1: Create a custom exception class
// ---------------------------------------
// Custom exceptions extend Exception (checked exception)
// or RuntimeException (unchecked exception).
package customer_exception;

class InvalidAgeException extends Exception {
    // Constructor that takes a message to describe the error
    public InvalidAgeException(String message) {
        super(message); // Call the parent Exception constructor
    }
}

public class Custom_Exception{
    // Method to check if a person is old enough to vote
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            // If age < 18, throw our custom exception
            throw new InvalidAgeException("Age " + age + " is too young to vote. Minimum age is 18.");
        } else {
            System.out.println("Age " + age + " is valid. You can vote!");
        }
    }

    public static void main(String[] args) {
        try {
            // Step 2: Call method with invalid age to trigger exception
            checkAge(14);

            // Step 3: Call method with valid age (this won’t throw exception)
            checkAge(25);

        } catch (InvalidAgeException e) {
            // Step 4: Catch the custom exception and display message
            System.out.println("Custom Exception Caught: " + e.getMessage());
        }
    }
}
