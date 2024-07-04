package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidJuriException extends Exception {

  public InvalidJuriException() {
  }

  public InvalidJuriException(String message) {
    super(message);
  }

  public InvalidJuriException(Throwable cause) {
    super(cause);
  }

  public InvalidJuriException(String message, Throwable cause) {
    super(message, cause);
  }
}
