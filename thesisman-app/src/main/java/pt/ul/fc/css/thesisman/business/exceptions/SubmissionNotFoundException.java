package pt.ul.fc.css.thesisman.business.exceptions;

public class SubmissionNotFoundException extends Exception {

  public SubmissionNotFoundException() {
  }

  public SubmissionNotFoundException(String message) {
    super(message);
  }

  public SubmissionNotFoundException(Throwable cause) {
    super(cause);
  }

  public SubmissionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
