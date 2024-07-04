package pt.ul.fc.css.thesisman.business.services.dtos;

import pt.ul.fc.css.thesisman.business.objects.Statistics;

public record StatisticsDTO(long totalStudents, long totalApproved, long totalUnapproved, double mean) {

  public static StatisticsDTO fromStatistics(Statistics statistics) {
    long totalStudents = statistics.getTotalStudents();
    long totalApproved = statistics.getTotalApproved();
    long totalUnapproved = statistics.getTotalUnapproved();
    double mean = statistics.getMeanGrade();
    return new StatisticsDTO(totalStudents, totalApproved, totalUnapproved, mean);
  }
}
