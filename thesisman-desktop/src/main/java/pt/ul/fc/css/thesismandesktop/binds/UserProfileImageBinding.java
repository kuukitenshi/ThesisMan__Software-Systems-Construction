package pt.ul.fc.css.thesismandesktop.binds;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.scene.image.Image;

public class UserProfileImageBinding extends ObjectBinding<Image> {

  private final StringBinding username;

  public UserProfileImageBinding(StringBinding username) {
    this.username = username;
    bind(username);
  }

  @Override
  protected Image computeValue() {
    try{
      return new Image("/static/images/" + username.get());
    } catch (Exception e){
      try {
        return new Image("/static/images/user.png");
      } catch (Exception e2) {
        return null;
      }
    }
  }
}
