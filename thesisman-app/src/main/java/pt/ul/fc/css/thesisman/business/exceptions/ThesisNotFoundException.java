package pt.ul.fc.css.thesisman.business.exceptions;

public class ThesisNotFoundException extends Exception {

  public ThesisNotFoundException() {
  }

  public ThesisNotFoundException(String message) {
    super(message);
  }

  public ThesisNotFoundException(Throwable cause) {
    super(cause);
  }

  public ThesisNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
