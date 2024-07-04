package pt.ul.fc.css.thesismandesktop.services;

import java.time.LocalDate;
import java.time.Month;

public class YearCalculatorService {

  public String calculateYear() {
    int year = LocalDate.now().getYear();
    Month month = LocalDate.now().getMonth();
    if (month.getValue() < 9) {
      return (year - 1) + "/" + year;
    } else {
      return year + "/" + (year + 1);
    }
  }
}
