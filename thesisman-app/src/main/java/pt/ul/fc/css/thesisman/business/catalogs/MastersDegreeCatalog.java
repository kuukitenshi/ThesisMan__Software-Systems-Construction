package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.MastersDegree;
import pt.ul.fc.css.thesisman.business.repositories.MastersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MastersDegreeCatalog {

  @Autowired private MastersRepository mastersRepository;

  public List<MastersDegree> getMastersDegrees() {
    List<MastersDegree> mastersDegrees = new ArrayList<>();
    this.mastersRepository.findAll().forEach(mastersDegrees::add);
    return mastersDegrees;
  }

}
