package pt.ul.fc.css.thesisman.business.objects;

public class Statistics {

  private long totalStudents = 0;
  private long totalApproved = 0;
  private long totalUnapproved = 0;
  private double meanGrade = 0;

  public double getMeanGrade() {
    return meanGrade;
  }

  public void setMeanGrade(double meanGrade) {
    this.meanGrade = meanGrade;
  }

  public long getTotalApproved() {
    return totalApproved;
  }

  public void setTotalApproved(long totalApproved) {
    this.totalApproved = totalApproved;
  }

  public long getTotalStudents() {
    return totalStudents;
  }

  public void setTotalStudents(long totalStudents) {
    this.totalStudents = totalStudents;
  }

  public long getTotalUnapproved() {
    return totalUnapproved;
  }

  public void setTotalUnapproved(long totalUnapproved) {
    this.totalUnapproved = totalUnapproved;
  }
}
