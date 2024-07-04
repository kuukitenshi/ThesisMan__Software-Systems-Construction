package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Theme;
import pt.ul.fc.css.thesisman.business.repositories.ThemeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ThemeCatalog {

  @Autowired private ThemeRepository themeRepository;

  public Optional<Theme> getById(Long themeId) {
    return this.themeRepository.findById(themeId);
  }

  public List<Theme> getThemes() {
    List<Theme> themes = new ArrayList<>();
    this.themeRepository.findAll().forEach(themes::add);
    return themes;
  }

}
