package pt.ul.fc.css.thesisman.business.exceptions;

public class AlreadyHasFinalSubmissionException extends Exception {

  public AlreadyHasFinalSubmissionException() {
  }

  public AlreadyHasFinalSubmissionException(String message) {
    super(message);
  }

  public AlreadyHasFinalSubmissionException(Throwable cause) {
    super(cause);
  }

  public AlreadyHasFinalSubmissionException(String message, Throwable cause) {
    super(message, cause);
  }
}
