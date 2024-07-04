package pt.ul.fc.css.thesisman.business.services.dtos;

import java.io.Serializable;

public interface IUserDTO extends Serializable {

  Long id();

  String username();

  String name();
}
