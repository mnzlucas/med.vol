package med.voll.api.domain.scheduling;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SchedulingAppointmentRepository extends JpaRepository<SchedulingAppointment, Long> {
    boolean existsByMedicoIdAndDataConsulta(@NotNull Long idMedico, @NotNull @Future LocalDateTime dateTime);
}
