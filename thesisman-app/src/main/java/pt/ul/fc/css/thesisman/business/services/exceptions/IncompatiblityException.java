package pt.ul.fc.css.thesisman.business.services.exceptions;

public class IncompatiblityException extends Exception {

  public IncompatiblityException() {}

  public IncompatiblityException(String message) {
    super(message);
  }

  public IncompatiblityException(String message, Throwable cause) {
    super(message, cause);
  }

  public IncompatiblityException(Throwable cause) {
    super(cause);
  }
}
