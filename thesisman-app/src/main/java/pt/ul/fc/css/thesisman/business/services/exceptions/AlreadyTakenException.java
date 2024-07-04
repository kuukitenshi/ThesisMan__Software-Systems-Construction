package pt.ul.fc.css.thesisman.business.services.exceptions;

public class AlreadyTakenException extends Exception {

  public AlreadyTakenException() {}

  public AlreadyTakenException(String message) {
    super(message);
  }

  public AlreadyTakenException(String message, Throwable cause) {
    super(message, cause);
  }

  public AlreadyTakenException(Throwable cause) {
    super(cause);
  }
}
