package pt.ul.fc.css.thesismandesktop.factories;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pt.ul.fc.css.thesismandesktop.model.objects.Submission;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SubmissionCellFactory implements Callback<ListView<Submission>, ListCell<Submission>> {

  @Override
  public ListCell<Submission> call(ListView<Submission> param) {
    return new ListSubmissionCell();
  }

  private static class ListSubmissionCell extends ListCell<Submission> {

    @Override
    protected void updateItem(Submission item, boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        setText(null);
      } else {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateProposalSub = LocalDateTime.ofInstant(item.getDate().toInstant(), ZoneId.systemDefault()).format(formatter);
        int whiteSpaces = 60;
        String stringProposal = "File name: " + item.getFilename() + String.format("%" + whiteSpaces + "s", "") + "Date: " + dateProposalSub;
        setText(stringProposal);
      }
    }
  }
}
