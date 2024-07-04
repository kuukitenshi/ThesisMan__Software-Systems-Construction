package pt.ul.fc.css.thesisman.business.validators;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Defence;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleValidator {

  public boolean hasConflict(LocalDateTime start, LocalDateTime end, List<Defence> others) {
    for (Defence defence : others) {
      LocalDateTime otherStart = defence.getDateTime();
      LocalDateTime otherEnd = otherStart.plusMinutes(defence.getDuration());
      System.out.println("CHECKING:\n" + start + " - " + end + "\n" + otherStart + " - " + otherEnd);
      if (start.isAfter(otherStart) && start.isBefore(otherEnd))
        return true;
      if (end.isAfter(otherStart) && end.isBefore(otherEnd))
        return true;
      if (otherStart.isAfter(start) && otherStart.isBefore(end))
        return true;
      if (otherEnd.isAfter(start) && otherEnd.isBefore(end))
        return true;
      if (start.isEqual(otherStart) || end.isEqual(otherEnd))
        return true;
    }
    return false;
  }

}
