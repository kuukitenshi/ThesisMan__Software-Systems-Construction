package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidYearException extends Exception {

  public InvalidYearException() {
  }

  public InvalidYearException(String message) {
    super(message);
  }

  public InvalidYearException(Throwable cause) {
    super(cause);
  }

  public InvalidYearException(String message, Throwable cause) {
    super(message, cause);
  }
}
