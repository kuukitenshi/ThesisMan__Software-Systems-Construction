package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidNameException extends Exception {

  public InvalidNameException() {
  }

  public InvalidNameException(String message) {
    super(message);
  }

  public InvalidNameException(Throwable cause) {
    super(cause);
  }

  public InvalidNameException(String message, Throwable cause) {
    super(message, cause);
  }
}
