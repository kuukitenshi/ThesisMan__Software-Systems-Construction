package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidRemunerationException extends Exception {

  public InvalidRemunerationException() {
  }

  public InvalidRemunerationException(String message) {
    super(message);
  }

  public InvalidRemunerationException(Throwable cause) {
    super(cause);
  }

  public InvalidRemunerationException(String message, Throwable cause) {
    super(message, cause);
  }
}
