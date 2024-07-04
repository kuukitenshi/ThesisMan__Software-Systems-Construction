package pt.ul.fc.css.thesisman.business.exceptions;

public class ApplicationNotFoundException extends Exception {

  public ApplicationNotFoundException() {
  }

  public ApplicationNotFoundException(String message) {
    super(message);
  }

  public ApplicationNotFoundException(Throwable cause) {
    super(cause);
  }

  public ApplicationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
