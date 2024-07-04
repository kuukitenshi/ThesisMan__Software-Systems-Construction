package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidTitleException extends Exception {

  public InvalidTitleException() {
  }

  public InvalidTitleException(String message) {
    super(message);
  }

  public InvalidTitleException(Throwable cause) {
    super(cause);
  }

  public InvalidTitleException(String message, Throwable cause) {
    super(message, cause);
  }
}
