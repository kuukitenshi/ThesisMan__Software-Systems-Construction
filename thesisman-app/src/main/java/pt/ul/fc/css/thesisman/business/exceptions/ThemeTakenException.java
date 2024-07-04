package pt.ul.fc.css.thesisman.business.exceptions;

public class ThemeTakenException extends Exception {

  public ThemeTakenException() {
  }

  public ThemeTakenException(String message) {
    super(message);
  }

  public ThemeTakenException(Throwable cause) {
    super(cause);
  }

  public ThemeTakenException(String message, Throwable cause) {
    super(message, cause);
  }
}
