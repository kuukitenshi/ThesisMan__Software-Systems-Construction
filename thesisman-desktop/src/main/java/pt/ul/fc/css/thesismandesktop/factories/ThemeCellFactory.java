package pt.ul.fc.css.thesismandesktop.factories;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pt.ul.fc.css.thesismandesktop.controllers.ThemeCellController;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.Theme;
import pt.ul.fc.css.thesismandesktop.services.ApplicationService;

import java.io.IOException;

public class ThemeCellFactory implements Callback<ListView<Theme>, ListCell<Theme>> {

  private final ObservableList<Application> applications;

  public ThemeCellFactory(ObservableList<Application> applications) {
    this.applications = applications;
  }

  @Override
  public ListCell<Theme> call(ListView<Theme> param) {
    try {
      return new ListViewThemeCell(applications);
    } catch (IOException e) {
      return null;
    }
  }

  private static class ListViewThemeCell extends ListCell<Theme> {

    private final Node node;
    private final ThemeCellController controller;

    private ListViewThemeCell(ObservableList<Application> applications) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/themeCell.fxml"));
      this.node = loader.load();
      this.controller = loader.getController();
      this.controller.initList(applications);
    }

    private final ApplicationService applicationService = new ApplicationService();

    @Override
    protected void updateItem(Theme th, boolean empty) {
      super.updateItem(th, empty);
      if (empty || th == null) {
        setGraphic(null);
        return;
      }
      this.controller.updateTheme(th);
      setGraphic(node);
    }
  }
}
