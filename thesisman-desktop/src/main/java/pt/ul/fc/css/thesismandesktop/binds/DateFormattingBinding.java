package pt.ul.fc.css.thesismandesktop.binds;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormattingBinding extends StringBinding {

  private final ObjectBinding<Object> dateProperty;
  private final SimpleDateFormat formatter;

  public DateFormattingBinding(ObjectBinding<Object> dateProperty, SimpleDateFormat formatter) {
    this.dateProperty = dateProperty;
    this.formatter = formatter;
    bind(dateProperty);
  }

  @Override
  protected String computeValue() {
    if (this.dateProperty.get() instanceof Date date)
      return formatter.format(date);
    return null;
  }
}
