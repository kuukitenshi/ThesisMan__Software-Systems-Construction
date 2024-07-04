module thesisman.desktop {
  requires com.fasterxml.jackson.databind;
  requires java.net.http;
  requires javafx.base;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires org.apache.httpcomponents.core5.httpcore5;
  requires org.apache.httpcomponents.client5.httpclient5;
  requires org.slf4j;

  exports pt.ul.fc.css.thesismandesktop;
  exports pt.ul.fc.css.thesismandesktop.controllers;
  exports pt.ul.fc.css.thesismandesktop.model.objects;
  exports pt.ul.fc.css.thesismandesktop.services.objects;

  opens pt.ul.fc.css.thesismandesktop.controllers;
  exports pt.ul.fc.css.thesismandesktop.model;
}
