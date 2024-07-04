package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.MastersDegree;
import pt.ul.fc.css.thesisman.business.entities.Theme;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidYearException;
import pt.ul.fc.css.thesisman.business.exceptions.MastersDegreeNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.MastersRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThemeRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ListThemesHandler {

  @Autowired private ThemeRepository themeRepository;
  @Autowired private MastersRepository mastersRepository;

  public List<Theme> listThemesInYear(String year, Long mastersId) throws InvalidYearException, MastersDegreeNotFoundException {
    if (year == null || year.isBlank()) {
      throw new InvalidYearException();
    }
    Optional<MastersDegree> mastersOpt = this.mastersRepository.findById(mastersId);
    if (mastersOpt.isEmpty()) {
      throw new MastersDegreeNotFoundException();
    }
    MastersDegree masters = mastersOpt.get();
    List<Theme> themes = this.themeRepository.findByYear(year);
    return themes.stream().filter(t -> t.getCompatibleMasters()
            .stream()
            .anyMatch(m -> m.getId().equals(masters.getId())))
            .toList();
  }
}
