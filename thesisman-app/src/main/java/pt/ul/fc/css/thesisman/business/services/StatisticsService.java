package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.handlers.StatisticsStudentSuccessHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.StatisticsDTO;

@Service
public class StatisticsService {

  @Autowired private StatisticsStudentSuccessHandler statisticsHandler;

  public StatisticsDTO getStatistics() {
    return StatisticsDTO.fromStatistics(this.statisticsHandler.getStatistics());
  }
}
