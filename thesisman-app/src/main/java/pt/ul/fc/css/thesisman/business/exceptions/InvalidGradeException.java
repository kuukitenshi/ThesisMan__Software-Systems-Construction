package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidGradeException extends Exception {

  public InvalidGradeException() {
  }

  public InvalidGradeException(String message) {
    super(message);
  }

  public InvalidGradeException(Throwable cause) {
    super(cause);
  }

  public InvalidGradeException(String message, Throwable cause) {
    super(message, cause);
  }
}
