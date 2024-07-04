package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.ClassRoom;
import pt.ul.fc.css.thesisman.business.repositories.ClassRoomRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassRoomCatalog {

  @Autowired private ClassRoomRepository classRoomRepository;

  public List<ClassRoom> getClassRooms() {
    List<ClassRoom> classRooms = new ArrayList<>();
    this.classRoomRepository.findAll().forEach(classRooms::add);
    return classRooms;
  }
}
