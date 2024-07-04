package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidSubmissionTypeException extends Exception {

  public InvalidSubmissionTypeException() {
  }

  public InvalidSubmissionTypeException(String message) {
    super(message);
  }

  public InvalidSubmissionTypeException(Throwable cause) {
    super(cause);
  }

  public InvalidSubmissionTypeException(String message, Throwable cause) {
    super(message, cause);
  }
}
