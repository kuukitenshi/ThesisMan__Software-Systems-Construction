package pt.ul.fc.css.thesisman.business.exceptions;

public class IncompatibleMastersException extends Exception {

  public IncompatibleMastersException() {
  }

  public IncompatibleMastersException(String message) {
    super(message);
  }

  public IncompatibleMastersException(Throwable cause) {
    super(cause);
  }

  public IncompatibleMastersException(String message, Throwable cause) {
    super(message, cause);
  }
}
