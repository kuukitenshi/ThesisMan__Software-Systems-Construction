package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.ClassRoomCatalog;
import pt.ul.fc.css.thesisman.business.services.dtos.ClassRoomDTO;

import java.util.List;

@Service
public class ClassRoomService {

  @Autowired private ClassRoomCatalog classRoomCatalog;

  public List<ClassRoomDTO> getClassRooms() {
    return this.classRoomCatalog.getClassRooms().stream().map(ClassRoomDTO::fromClassRoom).toList();
  }

}
