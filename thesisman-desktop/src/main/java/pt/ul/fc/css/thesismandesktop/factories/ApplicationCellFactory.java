package pt.ul.fc.css.thesismandesktop.factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pt.ul.fc.css.thesismandesktop.controllers.AppCellController;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;

import java.io.IOException;

public class ApplicationCellFactory implements Callback<ListView<Application>, ListCell<Application>> {

  @Override
  public ListCell<Application> call(ListView<Application> param) {
    try {
      return new ListViewAppCell(param);
    } catch (IOException e) {
      return null;
    }
  }

  private static class ListViewAppCell extends ListCell<Application> {

    private final Node node;
    private final AppCellController controller;

    private ListViewAppCell(ListView<Application> listView) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/appCell.fxml"));
      this.node = loader.load();
      this.controller = loader.getController();
      this.controller.initList(listView);
    }

    @Override
    protected void updateItem(Application app, boolean empty) {
      super.updateItem(app, empty);
      if (empty || app == null) {
        setGraphic(null);
        return;
      }
      this.controller.updateApplication(app);
      setGraphic(node);
    }
  }
}
