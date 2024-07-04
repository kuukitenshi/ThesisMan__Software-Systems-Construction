package pt.ul.fc.css.thesisman.business.exceptions;

public class AlreadyHasApplicationException extends Exception {

  public AlreadyHasApplicationException() {
  }

  public AlreadyHasApplicationException(String message) {
    super(message);
  }

  public AlreadyHasApplicationException(Throwable cause) {
    super(cause);
  }

  public AlreadyHasApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
