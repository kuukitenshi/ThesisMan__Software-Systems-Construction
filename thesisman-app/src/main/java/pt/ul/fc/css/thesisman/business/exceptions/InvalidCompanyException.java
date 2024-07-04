package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidCompanyException extends Exception {

  public InvalidCompanyException() {
  }

  public InvalidCompanyException(String message) {
    super(message);
  }

  public InvalidCompanyException(Throwable cause) {
    super(cause);
  }

  public InvalidCompanyException(String message, Throwable cause) {
    super(message, cause);
  }
}
