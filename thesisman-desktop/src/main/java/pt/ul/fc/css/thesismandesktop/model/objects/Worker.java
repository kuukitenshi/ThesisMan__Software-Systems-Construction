package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Worker extends GeneralUser {

  private final StringProperty company = new SimpleStringProperty();

  public StringProperty companyProperty() {
    return this.company;
  }
  public final String getCompany() {
    return companyProperty().get();
  }
  public final void setCompany(String company) {
    companyProperty().set(company);
  }


}
