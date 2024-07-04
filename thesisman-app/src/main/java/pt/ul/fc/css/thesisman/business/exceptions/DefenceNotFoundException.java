package pt.ul.fc.css.thesisman.business.exceptions;

public class DefenceNotFoundException extends Exception {

  public DefenceNotFoundException() {
  }

  public DefenceNotFoundException(String message) {
    super(message);
  }

  public DefenceNotFoundException(Throwable cause) {
    super(cause);
  }

  public DefenceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
