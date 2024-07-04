package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidDescriptionException extends Exception {

  public InvalidDescriptionException() {
  }

  public InvalidDescriptionException(String message) {
    super(message);
  }

  public InvalidDescriptionException(Throwable cause) {
    super(cause);
  }

  public InvalidDescriptionException(String message, Throwable cause) {
    super(message, cause);
  }
}
