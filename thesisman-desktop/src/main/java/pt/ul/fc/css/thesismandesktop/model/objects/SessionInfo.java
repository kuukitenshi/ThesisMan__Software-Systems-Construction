package pt.ul.fc.css.thesismandesktop.model.objects;

import java.time.LocalDate;
import java.time.Month;

public class SessionInfo {

  private static SessionInfo instance;
  private Student user;
  private String year;
  private Application appToCancel;

  private SessionInfo() {
  }

  public static SessionInfo getInstance() {
    if (instance == null) {
      instance = new SessionInfo();
    }
    return instance;
  }

  public void setUser(Student user) {
    this.user = user;
  }

  public Student getUser() {
    return user;
  }

  private String calculateYear() {
    int year = LocalDate.now().getYear();
    Month month = LocalDate.now().getMonth();
    if(month.getValue() < 9) {
      return (year - 1) + "/" + year;
    }else {
      return year + "/" + (year + 1);
    }
  }

  public String getYear() {
    if (year == null) {
      year = calculateYear();
    }
    return year;
  }

  public void setAppToCancel(Application appToCancel) {
    this.appToCancel = appToCancel;
  }

  public Application getAppToCancel() {
    return appToCancel;
  }

}
