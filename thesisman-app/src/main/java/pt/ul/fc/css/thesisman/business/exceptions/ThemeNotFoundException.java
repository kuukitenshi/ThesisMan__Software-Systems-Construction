package pt.ul.fc.css.thesisman.business.exceptions;

public class ThemeNotFoundException extends Exception {

  public ThemeNotFoundException() {
  }

  public ThemeNotFoundException(String message) {
    super(message);
  }

  public ThemeNotFoundException(Throwable cause) {
    super(cause);
  }

  public ThemeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
