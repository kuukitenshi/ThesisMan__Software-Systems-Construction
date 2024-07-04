package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.MastersDegreeCatalog;
import pt.ul.fc.css.thesisman.business.services.dtos.MastersDegreeDTO;

import java.util.List;

@Service
public class MastersDegreeService {

  @Autowired private MastersDegreeCatalog mastersDegreeCatalog;

  public List<MastersDegreeDTO> getMastersDegrees() {
    return this.mastersDegreeCatalog.getMastersDegrees().stream().map(MastersDegreeDTO::fromMastersDegree).toList();
  }

}
