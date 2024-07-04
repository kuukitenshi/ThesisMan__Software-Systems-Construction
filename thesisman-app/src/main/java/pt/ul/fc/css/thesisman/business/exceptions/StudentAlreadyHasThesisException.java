package pt.ul.fc.css.thesisman.business.exceptions;

public class StudentAlreadyHasThesisException extends Exception {

  public StudentAlreadyHasThesisException() {
  }

  public StudentAlreadyHasThesisException(String message) {
    super(message);
  }

  public StudentAlreadyHasThesisException(Throwable cause) {
    super(cause);
  }

  public StudentAlreadyHasThesisException(String message, Throwable cause) {
    super(message, cause);
  }
}
