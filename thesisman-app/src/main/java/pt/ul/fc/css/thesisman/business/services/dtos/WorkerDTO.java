package pt.ul.fc.css.thesisman.business.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Worker;

public record WorkerDTO(Long id, @NotBlank @Size(min = 1, max = 50) String username, @NotBlank @Size(min = 1, max = 30) String name, @NotBlank @Size(min = 1, max = 200) String company) implements IUserDTO  {

  public static WorkerDTO fromWorker(Worker worker) {
    if (worker == null)
      return null;
    Long id = worker.getId();
    String username = worker.getUsername();
    String name = worker.getName();
    String company = worker.getCompany();
    return new WorkerDTO(id, username, name, company);
  }
}
